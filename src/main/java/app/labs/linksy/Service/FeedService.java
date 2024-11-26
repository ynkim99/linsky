package app.labs.linksy.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.labs.linksy.DAO.FeedRepository;
import app.labs.linksy.Model.Feed;

@Service
public class FeedService implements IFeedService{
	
	@Autowired
	private FeedRepository feedRepository;
	
	// 피드 데이터 가져오기
    public List<Feed> getFeedsWithDetails() {
        return feedRepository.getFeedsWithDetails();
    }
	
    public void likeFeed(int feedId, String userId) {
        feedRepository.addLike(feedId, userId);
    }

    public void unlikeFeed(int feedId, String userId) {
        feedRepository.removeLike(feedId, userId);
    }
    
    public boolean isUserLikedFeed(int feedId, String userId) {
        return feedRepository.isUserLikedFeed(feedId, userId);
    }
    
    public int getLikeAmount(int feedId) {
        return feedRepository.getLikeAmount(feedId);
    }

    public List<Map<String, String>> getFeedLikes(int feedId) {
        return feedRepository.getFeedLikes(feedId);
    }
    
    public String getUserByFeedId(int feedId) {
        return feedRepository.getUserByFeedId(feedId);
    }
    
}
