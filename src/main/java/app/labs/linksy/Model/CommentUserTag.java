package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentUserTag {
	int userCommentTagId;
	int commentId;
	String userId;
}
