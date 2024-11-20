package app.labs.linksy.Model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Feed {
	private int feedId;
	private String userId;
	private String feedContent;
	private Timestamp feedTime;
	private int likeAmount;
	private List<FeedImage> feedImages;
	private Member member;
}
