package app.labs.linksy.DAO;

import java.util.List;

import app.labs.linksy.Model.Feed;
import app.labs.linksy.Model.Member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SearchRepository {
	List<Member> searchMembers(@Param("keyword") String keyword);
    List<Feed> findFeedsByKeyword(@Param("keyword") String keyword);
    List<Feed> findFeedsByHashtag(@Param("keyword") String keyword);
}
