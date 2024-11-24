package app.labs.linksy.Controller;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 * 메인 페이지 호출
	 */
	@GetMapping("")
	public String main(Model model, HttpSession session) {
		// 세션에서 사용자 ID 가져오기
		String userId = (String) session.getAttribute("userId");
		System.out.println("Fetching member with userId: " + userId);

		// 사용자 인증 확인
		if (userId == null || userId.isEmpty()) {
			System.out.println("User is not logged in.");
			return "redirect:/login"; // 로그인 페이지로 리다이렉션
		}

		// 사용자 정보 가져오기
		Member member = memberService.getMemberByUserId(userId);
		if (member == null) {
			throw new IllegalArgumentException("회원 정보가 존재하지 않습니다: " + userId);
		}

		// 피드 데이터 가져오기
		List<Feed> feeds = feedService.getFeedsWithDetails();
		System.out.println("Feeds List: " + feeds);

		for (Feed feed : feeds) {
			if (feed == null) {
				System.out.println("Null feed found in the list!");
			} else {
				System.out.println("Feed ID: " + feed.getFeedId());
				System.out.println("Feed Content: " + feed.getFeedContent());
			}
		}


// Null 값 필터링
		List<Integer> feedIds = feeds.stream()
				.filter(Objects::nonNull)
				.map(Feed::getFeedId)
				.collect(Collectors.toList());

		if (feedIds.isEmpty()) {
			System.out.println("Feed IDs are null or empty.");
		} else {
			Map<Integer, List<Comment>> commentsMap = commentService.getCommentsByFeedIds(feedIds);
			System.out.println("Comments map: " + commentsMap);
		}

		//// 피드에 댓글 추가
		//for (Feed feed : feeds) {
		//	feed.setComments(commentsMap.getOrDefault(feed.getFeedId(), Collections.emptyList()));
		//}

		// 모델에 데이터 추가
		model.addAttribute("feeds", feeds);
		model.addAttribute("member", member);

		return "main";
	}

	/**
	 * 댓글 가져오기 API
	 */
	//@GetMapping("/comments")
	//@ResponseBody
	//public List<Comment> getComments(@RequestParam("feedId") int feedId) {
	//	return commentService.getCommentsByFeedId(feedId);
	//}

	@GetMapping("/comments")
	@ResponseBody
	public List<Comment> getComments(@RequestParam("feedId") int feedId) {
		System.out.println("Fetching comments for feedId: " + feedId);

		// 단일 feedId를 리스트로 변환
		List<Comment> comments = commentService.getCommentsByFeedIds(Collections.singletonList(feedId)).get(feedId);

		return comments != null ? comments : Collections.emptyList();
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
