package app.labs.linksy.DAO;

import app.labs.linksy.Model.NotificationSettings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NotificationSettingsDAO {

    // 알림 설정 조회
    NotificationSettings getSettings(@Param("id") String id);

    // 알림 설정 저장 또는 업데이트
    void saveSettings(NotificationSettings settings);

    // 특정 알림 설정 업데이트
    void updateSetting(
            @Param("id") String id,
            @Param("key") String key,
            @Param("value") boolean value
    );

    // 알림 설정 삭제
    void deleteSettings(@Param("id") String id);
}
