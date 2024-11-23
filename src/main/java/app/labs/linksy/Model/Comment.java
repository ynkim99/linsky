package app.labs.linksy.Model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Comment {
	private int commentId;
	private String userId;
	private int feedId;
	private String commentContent;
	private int parentId;
	private Timestamp commentTime;
	
	private Member member;
	
}
