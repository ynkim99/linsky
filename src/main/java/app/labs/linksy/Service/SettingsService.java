package app.labs.linksy.Service;

import app.labs.linksy.DAO.SettingsDAO;
import app.labs.linksy.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class SettingsService {

    @Autowired
    private SettingsDAO settingsDAO;

    // 프로필 이미지 업데이트
    public void updateProfileImage(MultipartFile image, Member user) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("업로드된 파일이 비어 있습니다.");
        }

        // 파일 저장 경로 생성
        String uploadDir = "C:/labs_web/uploads/profile_images/"; // 실제 파일 저장 경로
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename(); // 고유 파일 이름 생성
        File destinationFile = new File(uploadDir, fileName);

        try {
            // 파일 저장
            image.transferTo(destinationFile);
        } catch (IOException e) {
            throw new RuntimeException("프로필 이미지 저장에 실패했습니다.", e);
        }

        // 파일 경로 저장 (데이터베이스)
        String imagePath = "/uploads/profile_images/" + fileName;
        settingsDAO.updateProfileImage(imagePath, user);
    }

    // 소개글 업데이트
    public void updateIntroduction(String introduction, Member user) {
        if (introduction == null || introduction.trim().isEmpty()) {
            throw new IllegalArgumentException("소개글이 비어 있습니다.");
        }
        settingsDAO.updateIntroduction(introduction, user);
    }

    // 알림 설정 업데이트
    public void updateNotificationSettings(boolean enabled, Member user) {
        settingsDAO.updateNotificationSettings(enabled, user);
    }
}
