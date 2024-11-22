package app.labs.linksy.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.labs.linksy.DAO.FeedRepository;
import app.labs.linksy.Model.Feed;

@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepository;
	
	public List<Feed> getAllFeeds() {
        return feedRepository.getAllFeeds();
    }
	
	public int likeFeed(int feedId, String userId) {
	    feedRepository.addLike(feedId, userId);
	    return feedRepository.getLikeAmount(feedId);
	}

	public int unlikeFeed(int feedId, String userId) {
	    feedRepository.removeLike(feedId, userId);
	    return feedRepository.getLikeAmount(feedId);
	}

    public List<Map<String, String>> getFeedLikes(int feedId) {
        return feedRepository.getFeedLikes(feedId);
    }

}
