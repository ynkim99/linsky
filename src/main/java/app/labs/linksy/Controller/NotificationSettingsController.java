package app.labs.linksy.Controller;

import app.labs.linksy.Model.NotificationSettings;
import app.labs.linksy.Service.NotificationSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings/notifications")
public class NotificationSettingsController {

    @Autowired
    private NotificationSettingsService notificationSettingsService;

    // 알림 설정 조회
    @GetMapping("/{id}")
    public NotificationSettings getNotificationSettings(@PathVariable String id) {
        return notificationSettingsService.getSettings(id);
    }

    // 알림 설정 저장
    @PostMapping
    public String saveNotificationSettings(@RequestBody NotificationSettings settings) {
        notificationSettingsService.saveSettings(settings); // 데이터 저장 호출
        return "알림 설정이 저장되었습니다.";
    }

    // 특정 알림 설정 업데이트
    @PutMapping("/{id}")
    public String updateNotificationSetting(
            @PathVariable String id,
            @RequestParam String key,
            @RequestParam boolean value) {
        notificationSettingsService.updateSetting(id, key, value);
        return "알림 설정이 성공적으로 업데이트되었습니다.";
    }

    // 알림 설정 삭제
    @DeleteMapping("/{id}")
    public String deleteNotificationSettings(@PathVariable String id) {
        notificationSettingsService.deleteSettings(id);
        return "알림 설정이 삭제되었습니다.";
    }
}
