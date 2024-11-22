package app.labs.linksy.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/search/feedPopup/{feedId}")
	public String getFeedPopup(@PathVariable("feedId") int feedId, Model model) {
		Feed feed = searchserv.getFeedById(feedId);
		model.addAttribute("feed", feed);
		return "searchPage/searchFeedPopup";
	}
	
	@GetMapping("/search/hiddenGame")
	public String hiddenGame() {
		return "searchPage/hiddenGame";
	}
	 
}
