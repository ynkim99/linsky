package app.labs.linksy.Service;

import app.labs.linksy.DAO.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 특정 USER_ID가 데이터베이스에 존재하는지 확인하는 메서드
    public boolean existsByUserId(String userId) {
        return userMapper.countUserById(userId) > 0;
    }

    // "anonymous" 사용자 추가 메서드 (필요시)
    public void createAnonymousUserIfNotExist() {
        if (!existsByUserId("anonymous")) {
            userMapper.insertAnonymousUser("anonymous", "Anonymous");
        }
    }
}
