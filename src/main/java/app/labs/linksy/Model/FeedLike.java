package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FeedLike {
	int userFeedLikeId;
	String userId;
	int feedId;
}
