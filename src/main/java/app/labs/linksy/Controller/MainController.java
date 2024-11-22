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

import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.FeedService;
import app.labs.linksy.Service.MemberService;

@Controller
@RequestMapping("/linksy")
public class MainController {
	
	@Autowired
    private MemberService memberService;
	
	@Autowired
	private FeedService feedService;	
	
	// 메인 페이지 호출
	@GetMapping(value="")
	public String main(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		 System.out.println("Fetching member with userId: " + userId);
		 
	     // DB에서 사용자 정보 가져오기
	     Member member = memberService.getMemberByUserId(userId);
	     
	     // 모든 피드 가져오기
	     List<Feed> feeds = feedService.getAllFeeds();
	     
	     if (member == null) {
	         System.out.println("No user found with ID: " + userId);
	     } else {
	         System.out.println("User found: " + member.toString());
	     }
	     
	     model.addAttribute("member", member);
	     model.addAttribute("feeds", feeds);
		return "main";
	}
	
	@PostMapping("/like")
    public int likeFeed(@RequestParam int feedId, @RequestParam String userId) {
        return feedService.likeFeed(feedId, userId);
    }

    @PostMapping("/unlike")
    public int unlikeFeed(@RequestParam int feedId, @RequestParam String userId) {
        return feedService.unlikeFeed(feedId, userId);
    }

    @GetMapping("/feed/{feedId}/likes")
    @ResponseBody
    public List<Map<String, String>> getFeedLikes(@PathVariable int feedId) {
        return feedService.getFeedLikes(feedId);
    }

}
