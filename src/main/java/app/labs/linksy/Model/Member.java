package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Member {
	private String userId;
    private String userName;
    private String userNickname;
    private String userPwd;
    private String userEmail;
    private String userImg;
    private String userIntroduce;
}
