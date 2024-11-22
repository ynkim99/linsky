package app.labs.linksy.DAO;

import app.labs.linksy.Model.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {
    // 알림 생성
    @Insert("""
     INSERT INTO NOTIFICATION (notification_id, user_id, noti_type, target_url, content)
            VALUES (NOTI_SEQ.NEXTVAL, #{userId}, #{notiType}, #{targetUrl}, #{content})
""")
    void insertNotification(Notification notification);


    // 특정 사용자의 알림 조회
    @Select("""
        SELECT * FROM NOTIFICATION
        WHERE user_id = #{userId}
        ORDER BY notification_id DESC
    """)
    List<Notification> getNotificationsByUserId(@Param("userId") String userId);

    @Select("SELECT notification_id, user_id, noti_type, target_url, content " +
            "FROM NOTIFICATION WHERE user_id = #{userId} ORDER BY notification_id DESC")
    List<Notification> getNotifications(@Param("userId") String userId);
}
