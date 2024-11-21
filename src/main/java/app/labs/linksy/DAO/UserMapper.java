package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 특정 USER_ID가 데이터베이스에 존재하는지 확인하는 메서드
    @Select("SELECT COUNT(*) FROM MEMBER WHERE USER_ID = #{userId}")
    int countUserById(@Param("userId") String userId);

    // "anonymous" 사용자 추가 메서드
    @Insert("INSERT INTO MEMBER (USER_ID, USER_NAME, USER_NICKNAME, USER_PWD) VALUES (#{userId}, #{userName}, 'Anonymous', 'default_password')")
    void insertAnonymousUser(@Param("userId") String userId, @Param("userName") String userName);
}
