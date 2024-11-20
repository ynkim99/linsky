package app.labs.linksy.Controller;

import app.labs.linksy.Model.NotificationSettings;
import app.labs.linksy.Service.NotificationSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationSettingsController {

    @Autowired
    private NotificationSettingsService notificationSettingsService;

    @GetMapping("/get-notification-settings")
    public NotificationSettings getNotificationSettings(@RequestParam String id) {
        return notificationSettingsService.getSettings(id);
    }

    @PostMapping("/save-notification-settings")
    public String saveNotificationSettings(@RequestBody NotificationSettings settings) {
        notificationSettingsService.saveSettings(settings);
        return "알림 설정이 저장되었습니다.";
    }
}
