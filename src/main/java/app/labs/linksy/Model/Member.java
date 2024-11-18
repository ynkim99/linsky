package app.labs.linksy.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class Member {
	String userId;
	String userName;
	String userNickname;
	String userPwd;
	String userEmail;
	String userImg;
	String userIntroduce;
}
