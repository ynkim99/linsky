package app.labs.linksy.Service;

import app.labs.linksy.DAO.FeedCreateMapper;
import app.labs.linksy.Model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedCreateService {

    @Autowired
    private FeedCreateMapper feedCreateMapper;

    /**
     * Create a new feed, including saving hashtags and images.
     */
    @Transactional
    public void createFeed(Feed feed, List<String> hashtags, List<String> images) {
        try {
            feedCreateMapper.insertFeed(feed);
            int feedId = feed.getFeedId(); // 자동 증가된 피드 ID 가져오기
            System.out.println("Feed inserted with ID: " + feedId); // 디버깅 로그

            // 해시태그 삽입
            for (String hashtag : hashtags) {
                int hashtagId = feedCreateMapper.insertHashtagAndReturnId(hashtag);
                System.out.println("Inserted hashtag with ID: " + hashtagId); // 디버깅 로그
                feedCreateMapper.insertFeedHashtag(feedId, hashtagId);
            }

            // 이미지 삽입
            for (String imgName : images) {
                feedCreateMapper.insertFeedImage(feedId, imgName);
                System.out.println("Inserted image with name: " + imgName); // 디버깅 로그
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 트랜잭션 롤백을 위해 예외 재던짐
        }
    }


    /**
     * Update an existing feed, including updating its hashtags and images.
     */
    @Transactional
    public void updateFeed(Feed feed, List<String> hashtags, List<String> images) {
        // Update the feed content
        feedCreateMapper.updateFeed(feed);

        int feedId = feed.getFeedId();

        // Remove existing hashtags and add new ones
        feedCreateMapper.deleteFeedHashtags(feedId);
        for (String hashtag : hashtags) {
            int hashtagId = feedCreateMapper.insertHashtagAndReturnId(hashtag);
            feedCreateMapper.insertFeedHashtag(feedId, hashtagId);
        }

        // Remove existing images and add new ones
        feedCreateMapper.deleteFeedImages(feedId);
        for (String imgName : images) {
            feedCreateMapper.insertFeedImage(feedId, imgName);
        }
    }

    /**
     * Delete a feed by its ID, including associated hashtags and images.
     */
    @Transactional
    public void deleteFeed(int feedId) {
        // Delete all associations first, then delete the feed
        feedCreateMapper.deleteFeedHashtags(feedId);
        feedCreateMapper.deleteFeedImages(feedId);
        feedCreateMapper.deleteFeed(feedId);
    }

    /**
     * Get a feed by its ID, including associated hashtags and images.
     */
    public Feed getFeedById(int feedId) {
        return feedCreateMapper.findFeedById(feedId);
    }
}
