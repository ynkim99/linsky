package app.labs.linksy.DAO;

import java.util.List;
import java.util.Map;

import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SearchRepository {
	List<Member> searchMembers(@Param("keyword") String keyword);
    List<Feed> searchFeeds(@Param("keyword") String keyword);
    List<Feed> findFeedsByKeyword(@Param("keyword") String keyword);
    List<Feed> findFeedsByHashtag(@Param("keyword") String keyword);
    List<Map<String, Object>> searchAll(@Param("keyword") String keyword);
}
