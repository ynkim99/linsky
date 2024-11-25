package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import app.labs.linksy.Model.Member;

@Mapper
public interface ProfileSettingRepository {
    void updateProfileImage(@Param("userId") String userId, @Param("userImg") String userImg);
    void updateUserIntroduce(@Param("userId") String userId, @Param("userIntroduce") String userIntroduce);
    void updateNickname(@Param("userId") String userId, @Param("nickname") String nickname);
    int updatePassword(@Param("userId") String userId, @Param("currentPassword") String currentPassword, @Param("newPassword") String newPassword);
    Member getMemberProfile(@Param("userId") String userId);
} 