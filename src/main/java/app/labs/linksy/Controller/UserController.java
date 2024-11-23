package app.labs.linksy.Controller;

import app.labs.linksy.DAO.UserMapper;
import app.labs.linksy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // 이미지 업로드 및 DB에 URL 저장 메서드
    @PostMapping("/uploadProfileImage")
    public String uploadProfileImage(@RequestParam("file") MultipartFile file,
                                     @RequestParam("userId") String userId) {
        // 업로드할 디렉토리 지정
        String uploadDir = "C:/Users/quien/Desktop/uploads/images/profile/";
        String fileName = file.getOriginalFilename();
        String imagePath = "/images/profile/" + fileName; // 이 경로를 DB에 저장할 것입니다.

        try {
            // 파일을 지정된 위치에 저장
            File saveFile = new File(uploadDir + fileName);
            file.transferTo(saveFile);

            // 사용자 프로필 이미지 경로를 DB에 업데이트
            userMapper.updateUserImageUrl(userId, imagePath);
            return "프로필 이미지가 성공적으로 업데이트되었습니다.";
        } catch (IOException e) {
            e.printStackTrace();
            return "이미지 업로드에 실패했습니다.";
        }
    }
}

