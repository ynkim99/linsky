package app.labs.linksy.Model;

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/ynkim2
}
