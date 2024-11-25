package app.labs.linksy.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.labs.linksy.Model.Comment;
import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.CommentService;
import app.labs.linksy.Service.FeedService;
import app.labs.linksy.Service.FollowService;
import app.labs.linksy.Service.HttpSessionService;
import app.labs.linksy.Service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/linksy")
public class MainController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FollowService followService;

	@Autowired
	private HttpSessionService httpSessionService;

	// 메인 페이지 호출
	@GetMapping("")
	public String main(Model model, HttpSession session) {
		
		String userId = httpSessionService.sessionConfirm(session);

		// 사용자 정보 가져오기
		Member member = memberService.getMemberByUserId(userId);
		if (member == null) {
			throw new IllegalArgumentException("회원 정보가 존재하지 않습니다: " + userId);
		}

		// 피드 데이터 가져오기
		List<Feed> feeds = feedService.getFeedsWithDetails();
		System.out.println("Feeds List: " + feeds);
		
		// 팔로우한 사용자 정보 가져오기
	    List<Member> followings = followService.getFollowings(userId);
	     
	    // 랜덤으로 7명 선택
	    Collections.shuffle(followings);
	    List<Member> limitedFollowings = followings.stream().limit(7).collect(Collectors.toList());
	  		 
	  	// 피드 리스트를 랜덤으로 섞기
	  	Collections.shuffle(feeds);

	  	// 각 피드에 댓글 데이터 추가
	    for (Feed feed : feeds) {
	        List<Comment> comments = commentService.getCommentsByFeedId(feed.getFeedId());
	        feed.setComments(comments); // Feed 모델에 comments 필드를 추가해야 합니다.
	 
	        // 좋아요 수 업데이트
	        feed.setLikeAmount(feedService.getLikeAmount(feed.getFeedId()));
	        // 사용자가 이 피드를 좋아요 했는지 확인
	        boolean isLiked = feedService.isUserLikedFeed(feed.getFeedId(), userId);
	        feed.setLikedByUser(isLiked);  // isLikedByUser 값 설정
	    }


		// 모델에 데이터 추가
	    model.addAttribute("feeds", feeds);
	    model.addAttribute("member", member);
	    model.addAttribute("followings", limitedFollowings);

		return "main";
	}
	
	// 댓글 가져오기
	@GetMapping("/comments")
    @ResponseBody
    public List<Comment> getComments(@RequestParam("feedId") int feedId) {
    	List<Comment> comments = commentService.getCommentsByFeedId(feedId);
        return comments;
    }
	
	// 댓글 작성하기
	@PostMapping("/addComment")
    @ResponseBody
    public Comment addComment(@RequestParam("feedId") int feedId, 
							@RequestParam("commentContent") String commentContent,
							HttpSession session) {
        
        String userId = (String) session.getAttribute("userId");
        
        Comment comment = new Comment();
        comment.setFeedId(feedId);
        comment.setCommentContent(commentContent);
        comment.setUserId(userId);

        // 댓글 저장
        commentService.addComment(comment);

        // 댓글 작성자 정보 가져오기
        Member member = memberService.getMemberByUserId(userId);
        comment.setMember(member);

        return comment; // 클라이언트에게 저장된 댓글 정보를 반환
    }
	
	@PostMapping("/toggleLike")
	@ResponseBody
	public Map<String, Object> toggleLike(@RequestParam("feedId") int feedId,
	                                      @RequestParam("userId") String userId) {
	    boolean isLiked = feedService.isUserLikedFeed(feedId, userId);

	    if (isLiked) {
	        // 이미 좋아요가 눌려져 있으면 좋아요 취소
	        feedService.unlikeFeed(feedId, userId);
	    } else {
	        // 좋아요가 눌려져 있지 않으면 좋아요 추가
	        feedService.likeFeed(feedId, userId);
	    }

	    // 변경된 좋아요 개수 가져오기
	    int likeAmount = feedService.getLikeAmount(feedId);

	    // 결과 반환
	    Map<String, Object> response = new HashMap<>();
	    response.put("isLiked", !isLiked);  // 변경된 좋아요 상태
	    response.put("likeAmount", likeAmount);  // 새로운 좋아요 개수

	    return response;
	}
	
	/**
	 * 특정 피드에 대한 좋아요 가져오기 API
	 */
	@GetMapping("/feed/{feedId}/likes")
	@ResponseBody
	public List<Map<String, String>> getFeedLikes(@PathVariable int feedId) {
		return feedService.getFeedLikes(feedId);
	}
}
