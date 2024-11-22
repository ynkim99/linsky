package app.labs.linksy.Service;

import app.labs.linksy.Model.NotificationSettings;

public interface NotificationSettingsService {
    NotificationSettings getSettings(String id); // 알림 설정 조회
    void saveSettings(NotificationSettings settings); // 알림 설정 저장
    void updateSetting(String id, String key, boolean value); // 특정 알림 설정 업데이트
    void deleteSettings(String id); // 알림 설정 삭제
}
