package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FeedLike {
	private int userFeedLikeId;
	private String userId;
	private int feedId;
}

