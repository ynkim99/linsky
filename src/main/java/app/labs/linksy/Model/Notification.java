package app.labs.linksy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Notification {
	int notificationId;
	String userId;
	String notiType;
	String targetUrl;
	String content;
}
