package app.labs.linksy.DAO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 특정 USER_ID가 데이터베이스에 존재하는지 확인하는 메서드
    @Select("SELECT COUNT(*) FROM MEMBER WHERE USER_ID = #{userId}")
    int countUserById(@Param("userId") String userId);

    // "anonymous" 사용자 추가 메서드
    @Insert("INSERT INTO MEMBER (USER_ID, USER_NAME, USER_NICKNAME, USER_PWD) VALUES (#{userId}, #{userName}, 'Anonymous', 'default_password')")
    void insertAnonymousUser(@Param("userId") String userId, @Param("userName") String userName);

    // 사용자 이미지 URL 업데이트 메서드 추가
    @Update("UPDATE MEMBER SET USER_IMG = #{userImg} WHERE USER_ID = #{userId}")
    void updateUserImageUrl(@Param("userId") String userId, @Param("userImg") String userImg);

    // 사용자 이미지 경로 가져오기
    @Select("SELECT USER_IMG FROM MEMBER WHERE USER_ID = #{userId}")
    String getUserImage(@Param("userId") String userId);

    @Select("<script>" +
            "SELECT USER_ID, USER_IMG " +
            "FROM MEMBER " +
            "WHERE USER_ID IN " +
            "<foreach item='id' collection='userIds' open='(' separator=',' close=')'>#{id}</foreach>" +
            "</script>")
    List<Map<String, String>> getUserImages(@Param("userIds") List<String> userIds);




}
