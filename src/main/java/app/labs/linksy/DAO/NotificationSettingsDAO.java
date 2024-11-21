package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.Mapper;

import app.labs.linksy.Model.NotificationSettings;

@Mapper
public interface NotificationSettingsDAO {
    NotificationSettings getSettings(String id);

    void saveSettings(NotificationSettings settings);
}
