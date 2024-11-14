package app.labs.linksy.Model;

import java.sql.Timestamp;

public class Feed {
	int feeId;
	String userId;
	// feedContent 이것도 clob 값이라 결정 되면 수정할 것
	Timestamp feedTime;
	int likeAmount;
}
