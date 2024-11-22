package app.labs.linksy.Controller;

import app.labs.linksy.Model.Notification;
import app.labs.linksy.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public ResponseEntity<String> addNotification(@RequestParam String userId,
                                                  @RequestParam(defaultValue = "LIKE") String notiType,
                                                  @RequestParam String content,
                                                  @RequestParam(required = false) String targetUrl) {
        notificationService.createNotification(userId, notiType, content, targetUrl);
        return ResponseEntity.ok("Notification added successfully.");
    }

    // 특정 사용자 알림 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable String userId) {
        List<Notification> notifications = notificationService.getNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

}
