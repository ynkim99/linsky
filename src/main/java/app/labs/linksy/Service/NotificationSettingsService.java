package app.labs.linksy.Service;

import app.labs.linksy.Model.NotificationSettings;

public interface NotificationSettingsService {
    NotificationSettings getSettings(String id);
    void saveSettings(NotificationSettings settings);
}
