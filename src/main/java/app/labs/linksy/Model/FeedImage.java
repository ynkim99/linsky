package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FeedImage {
	private int imageId;
	private int feedId;
	private String imgName;
}
