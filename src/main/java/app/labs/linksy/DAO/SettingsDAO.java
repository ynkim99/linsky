package app.labs.linksy.DAO;

import app.labs.linksy.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 프로필 이미지 업데이트
    public void updateProfileImage(String imagePath, Member user) {
        String sql = "UPDATE Member SET profile_image = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, imagePath, user.getUserId());
    }

    // 소개글 업데이트
    public void updateIntroduction(String introduction, Member user) {
        String sql = "UPDATE Member SET introduction = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, introduction, user.getUserId());
    }

    // 알림 설정 업데이트
    public void updateNotificationSettings(boolean enabled, Member user) {
        String sql = "UPDATE Member SET notifications_enabled = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, enabled, user.getUserId());
    }
}
