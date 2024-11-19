package app.labs.linksy.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchserv;
	
	@GetMapping("/search")
    public String showSearchPage(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        model.addAttribute("keyword", keyword);
        return "searchPage/search";
    }
	
	@GetMapping("/search/accounts")
    public String searchAccounts(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        if (!keyword.isEmpty()) {
            List<Member> members = searchserv.searchMembers(keyword);
            model.addAttribute("members", members);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeTab", "account");
        return "searchPage/search";
    }
	
	@GetMapping("/search/hashtag")
    public String searchHashtags(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        if (!keyword.isEmpty()) {
            List<Feed> hashtagFeeds = searchserv.searchFeedsByHashtag(keyword);
            model.addAttribute("hashtagFeeds", hashtagFeeds);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeTab", "hashtag");
        return "searchPage/search";
    }
	
	@GetMapping("/search/feed")
    public String searchFeeds(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        if (!keyword.isEmpty()) {
            List<Feed> feeds = searchserv.searchFeedsByKeyword(keyword);
            model.addAttribute("feeds", feeds);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeTab", "feed");
        return "searchPage/search";
    }
	
	
	
	
}
