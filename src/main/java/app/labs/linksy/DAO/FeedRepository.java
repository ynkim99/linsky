package app.labs.linksy.DAO;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import app.labs.linksy.Model.Feed;

@Mapper
public interface FeedRepository {
<<<<<<< HEAD
	List<Feed> getAllFeeds();
	void addLike(@Param("feedId") int feedId, @Param("userId") String userId);
    void removeLike(@Param("feedId") int feedId, @Param("userId") String userId);
    int getLikeAmount(@Param("feedId") int feedId);
    List<Map<String, String>> getFeedLikes(@Param("feedId") int feedId);
=======

	List<Feed> getFeedsWithDetails();
>>>>>>> origin/ynkim9
}
