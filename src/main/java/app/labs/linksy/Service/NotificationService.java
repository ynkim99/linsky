package app.labs.linksy.Service;

import app.labs.linksy.DAO.NotificationMapper;
import app.labs.linksy.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public void createNotification(String userId, String notiType, String content, String targetUrl) {
        // Notification 객체 생성 및 값 설정
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setNotiType(notiType);
        notification.setContent(content);
        notification.setTargetUrl(targetUrl != null ? targetUrl : ""); // null 값 처리

        // Mapper 호출
        notificationMapper.insertNotification(notification);
    }

    // 특정 사용자의 알림 조회
    public List<Notification> getNotifications(String userId) {
        return notificationMapper.getNotifications(userId);
    }
}
