package app.labs.linksy.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.labs.linksy.Model.Comment;
import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.CommentService;
import app.labs.linksy.Service.FeedService;
import app.labs.linksy.Service.MemberService;

@Controller
@RequestMapping("/linksy")
public class MainController {
	
	@Autowired
    private MemberService memberService;
	
	@Autowired
	private FeedService feedService;	
	
	@Autowired
	private CommentService commentService;
	
	// 메인 페이지 호출
	@GetMapping(value="")
	public String main(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		 System.out.println("Fetching member with userId: " + userId);
		 //String userId = "testUser"; // DB에서 가져올 사용자 ID (하드코딩된 값)
		 
	     // DB에서 사용자 정보 가져오기
	     Member member = memberService.getMemberByUserId(userId);
	     // 피드 데이터 가져오기
	     List<Feed> feeds = feedService.getFeedsWithDetails();
	     
	  // 각 피드에 댓글 데이터 추가
	     for (Feed feed : feeds) {
	         List<Comment> comments = commentService.getCommentsByFeedId(feed.getFeedId());
	         feed.setComments(comments); // Feed 모델에 comments 필드를 추가해야 합니다.
	     }
	     
	     model.addAttribute("feeds", feeds);
	     model.addAttribute("member", member);

		return "main";
	}
	
	// 댓글 가져오기 API
    @GetMapping("/comments")
    @ResponseBody
    public List<Comment> getComments(@RequestParam("feedId") int feedId) {
    	List<Comment> comments = commentService.getCommentsByFeedId(feedId);
        return comments;
        // return commentService.getCommentsByFeedId(feedId);
    }

    @GetMapping("/feed/{feedId}/likes")
    @ResponseBody
    public List<Map<String, String>> getFeedLikes(@PathVariable int feedId) {
        return feedService.getFeedLikes(feedId);
    }

}
