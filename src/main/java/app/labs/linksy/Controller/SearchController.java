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
import app.labs.linksy.Model.Comment;
import app.labs.linksy.Service.SearchService;
import app.labs.linksy.Service.CommentService;

@Controller 
public class SearchController {
	
	@Autowired
	SearchService searchserv;

    @Autowired
    CommentService commentService;
	
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
            for (Feed feed : hashtagFeeds) {
                int likeCount = searchserv.getLikeCount(feed.getFeedId());
                int commentCount = searchserv.getCommentCount(feed.getFeedId());
                feed.setLikeAmount(likeCount);
                feed.setCommentCount(commentCount);
            }
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
            for (Feed feed : feeds) {
                int likeCount = searchserv.getLikeCount(feed.getFeedId());
                int commentCount = searchserv.getCommentCount(feed.getFeedId());
                feed.setLikeAmount(likeCount);
                feed.setCommentCount(commentCount);
            }
            model.addAttribute("feeds", feeds);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeTab", "feed");
        return "searchPage/search";
    }
	
	@GetMapping("/search/feedPopup/{feedId}")
	public String getFeedPopup(@PathVariable("feedId") int feedId, Model model) {
		Feed feed = searchserv.getFeedById(feedId);
		List<Comment> comments = commentService.getCommentsByFeedId(feedId);
		model.addAttribute("feed", feed);
		model.addAttribute("comments", comments);
		return "searchPage/searchFeedPopup";
	}
	
	@GetMapping("/search/hiddenGame")
	public String hiddenGame() {
		return "searchPage/hiddenGame";
	}
	 
}
