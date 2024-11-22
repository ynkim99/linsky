package app.labs.linksy.Service;

import java.util.List;

import app.labs.linksy.Model.Feed;

public interface IFeedService {
	List<Feed> getFeedsWithDetails();
}
