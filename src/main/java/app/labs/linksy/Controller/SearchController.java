package app.labs.linksy.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/search")
    public String search(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        if (!keyword.isEmpty()) {
            List<Member> members = searchserv.searchMembers(keyword);
            List<Feed> feeds = searchserv.searchFeedsWithImages(keyword);
            List<Feed> hashtagFeeds = searchserv.searchFeedsByHashtag(keyword); 

            model.addAttribute("members", members);
            model.addAttribute("feeds", feeds);
            model.addAttribute("hashtagFeeds", hashtagFeeds);
        }

        model.addAttribute("keyword", keyword);
        return "searchPage/search"; 
    }
	
	
	
	
	
}
