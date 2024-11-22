package app.labs.linksy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.labs.linksy.DAO.SearchRepository;
import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;

@Service
public class SearchService {
	
	@Autowired
	SearchRepository searchrepo;
	
	public SearchService(SearchRepository searchrepo) {
        this.searchrepo = searchrepo;
    }

    public List<Member> searchMembers(String keyword) {
        return searchrepo.searchMembers(keyword);
    }

    public List<Feed> searchFeedsByKeyword(String keyword) {
        return searchrepo.findFeedsByKeyword(keyword);
    }

    public List<Feed> searchFeedsByHashtag(String keyword) {
        return searchrepo.findFeedsByHashtag(keyword);
    }

    public Feed getFeedById(int feedId) {
        return searchrepo.getFeedById(feedId);
    }

    public String getContentByFeedId(int feedId) {
        return searchrepo.getContentByFeedId(feedId);
    }

    public List<Feed> getFeedsByUserId(String userId) {
        return searchrepo.getFeedsByUserId(userId);
    }
}
