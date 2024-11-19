package app.labs.linksy.Model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Comment {
	int commentId;
	String userId;
	int feedId;
	String commentContent;
	int parentId;
	Timestamp commentTime;
}
