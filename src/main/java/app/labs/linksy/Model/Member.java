package app.labs.linksy.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	String userId;
	String userName;
	String userNickname;
	String userPwd;
	String userEmail;
	String userImg;
	String userIntroduce;
}
