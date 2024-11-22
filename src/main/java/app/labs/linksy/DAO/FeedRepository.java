package app.labs.linksy.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.labs.linksy.Model.Feed;

@Mapper
public interface FeedRepository {

	List<Feed> getFeedsWithDetails();
}
