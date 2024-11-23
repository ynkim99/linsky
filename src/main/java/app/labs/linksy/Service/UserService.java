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

    // 사용자 이미지 URL 업데이트 메서드
    public void updateUserImageUrl(String userId, String imageUrl) {
        // 사용자가 존재하는지 확인
        int userCount = userMapper.countUserById(userId);
        if (userCount > 0) {
            // 사용자 존재 시 이미지 URL 업데이트
            userMapper.updateUserImageUrl(userId, imageUrl);
        } else {
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다: " + userId);
        }
    }


    // 사용자 이미지 URL 가져오기
    public String getUserImageUrl(String userId) {
        return userMapper.getUserImage(userId); // UserMapper에서 사용자 이미지 경로 가져옴
    }

}
