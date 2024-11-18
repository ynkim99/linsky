package app.labs.linksy.Controller;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    // 프로필 편집 화면
    @GetMapping("/profile")
    public String showProfilePage() {
        return "settings/profile";
    }

    // 프로필 이미지 변경
    @PostMapping("/updateProfileImage")
    public String updateProfileImage(@RequestParam("image") MultipartFile image, @SessionAttribute("user") Member user) {
        if (image.isEmpty()) {
            return "redirect:/settings/profile?error=emptyFile";
        }
        try {
            settingsService.updateProfileImage(image, user);
        } catch (Exception e) {
            return "redirect:/settings/profile?error=uploadFailed";
        }
        return "redirect:/settings/profile?success=true";
    }

    // 소개글 편집
    @PostMapping("/updateIntroduction")
    public String updateIntroduction(@RequestParam String introduction, @SessionAttribute("user") Member user) {
        settingsService.updateIntroduction(introduction, user);
        return "redirect:/settings/profile?success=true";
    }

    // 알림 설정 화면
    @GetMapping("/notifications")
    public String showNotificationsPage() {
        return "settings/notifications";
    }

    // 알림 설정 토글
    @PostMapping("/toggleNotifications")
    public String toggleNotifications(@RequestParam("notificationsEnabled") boolean enabled, @SessionAttribute("user") Member user) {
        settingsService.updateNotificationSettings(enabled, user);
        return "redirect:/settings/notifications?success=true";
    }
}
