package app.labs.linksy.Service;

import app.labs.linksy.DAO.NotificationSettingsDAO;
import app.labs.linksy.Model.NotificationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSettingsServiceImpl implements NotificationSettingsService {

    @Autowired
    private NotificationSettingsDAO notificationSettingsDAO;

    @Override
    public NotificationSettings getSettings(String id) {
        return notificationSettingsDAO.getSettings(id);
    }

    @Override
    public void saveSettings(NotificationSettings settings) {
        notificationSettingsDAO.saveSettings(settings);
    }
}
