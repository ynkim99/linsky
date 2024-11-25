package app.labs.linksy.Service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import app.labs.linksy.exception.SessionExpiredException;

@Service
public class HttpSessionService {
	public String sessionConfirm(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			throw new SessionExpiredException("세션이 만료되었습니다. 다시 로그인해주세요.");
		}
		return userId;
	}
}
