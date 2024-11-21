package app.labs.linksy.Controller;

import app.labs.linksy.Model.Feed;
import app.labs.linksy.Service.FeedCreateService;
import app.labs.linksy.Service.UserService;
import app.labs.linksy.Util.HashtagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class FeedCreateController {

    @Autowired
    private FeedCreateService feedCreateService;

    @Autowired
    private UserService userService;  // 추가된 부분

    private static final Logger logger = Logger.getLogger(FeedCreateController.class.getName());

    // 게시물 생성 페이지 매핑
    @GetMapping("/feed/create")
    public String createFeedPage() {
        return "feed-create"; // feed-create.html 파일과 매핑
    }

    // 게시물 생성 성공 페이지 매핑
    @GetMapping("/createFeedSuccess")
    public String createFeedSuccessPage() {
        return "feed-create-success"; // feed-create-success.html 파일과 매핑
    }

    // 게시물 수정 페이지 매핑
    @GetMapping("/modifyFeedPage")
    public String modifyFeedPage() {
        return "feed-modify"; // feed-modify.html 파일과 매핑
    }

    // 게시물 수정 성공 페이지 매핑
    @GetMapping("/modifyFeedSuccess")
    public String modifyFeedSuccessPage() {
        return "feed-modify-success"; // feed-modify-success.html 파일과 매핑
    }

    // 게시물 삭제 성공 페이지 매핑
    @GetMapping("/deleteFeedSuccess")
    public String deleteFeedSuccessPage() {
        return "feed-delete-success"; // feed-delete-success.html 파일과 매핑
    }

    // 게시물 수정 또는 삭제 선택 페이지 매핑
    @GetMapping("/modifyOrDeleteFeedPage")
    public String modifyOrDeleteFeedPage() {
        return "feed-modifyordelete"; // feed-modifyordelete.html 파일과 매핑
    }

    // 좋아요 목록 페이지 매핑
    @GetMapping("/likesListPage")
    public String likesListPage() {
        return "likeslist"; // likeslist.html 파일과 매핑
    }

    // 알림 매핑
    @GetMapping("/notification")
    public String notificationPage() {
        return "notification"; // notification.html 파일과 매핑
    }

    // 전체 게시물 목록 페이지 매핑
    @GetMapping("/allFeedsPage")
    public String allFeedsPage() {
        return "list-Feed"; // list-Feed.html 파일과 매핑
    }

    // 변경된 업로드 경로를 가져옴
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @PostMapping("/feed/create")
    public String createFeed(@RequestParam(value = "userId", required = false) String userId,
                             @RequestParam("feedContent") String feedContent,
                             @RequestParam("images") List<MultipartFile> imageFiles) {
        logger.info("Received request to create feed for user: " + (userId != null ? userId : "anonymous"));

        // userId가 없거나 존재하지 않는 경우 기본 사용자로 설정
        if (userId == null || !userService.existsByUserId(userId)) {
            logger.warning("User ID does not exist, using default 'anonymous'");
            userId = "anonymous";

            // "anonymous" 사용자가 부모 테이블에 없는 경우 자동으로 생성
            userService.createAnonymousUserIfNotExist();
        }

        try {
            // 해시태그 추출
            List<String> hashtags = HashtagExtractor.extractHashtags(feedContent);
            logger.info("Extracted hashtags: " + hashtags);

            // 이미지 파일 저장 및 이름 수집
            List<String> imageNames = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                if (!imageFile.isEmpty()) {
                    String filePath = uploadDir + File.separator + imageFile.getOriginalFilename();
                    File destFile = new File(filePath);
                    try {
                        imageFile.transferTo(destFile);
                        imageNames.add(imageFile.getOriginalFilename());
                        logger.info("Saved image: " + imageFile.getOriginalFilename() + " at " + filePath);
                    } catch (IOException e) {
                        logger.severe("Failed to save image: " + imageFile.getOriginalFilename() + " due to " + e.getMessage());
                        throw e;
                    }
                }
            }

            // 피드 생성 및 데이터 저장
            Feed feed = new Feed();
            feed.setUserId(userId);
            feed.setFeedContent(feedContent);
            feed.setLikeAmount(0); // 초기 좋아요 수

            feedCreateService.createFeed(feed, hashtags, imageNames);
            logger.info("Feed successfully created for user: " + userId);

            return "redirect:/createFeedSuccess";

        } catch (IOException e) {
            logger.severe("Error occurred while creating feed: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        }
    }


    @PostMapping("/feed/update")
    public String updateFeed(@RequestParam("feedId") int feedId,
                             @RequestParam(value = "userId", required = false) String userId,
                             @RequestParam("feedContent") String feedContent,
                             @RequestParam("images") List<MultipartFile> imageFiles) {
        logger.info("Received request to update feed for user: " + (userId != null ? userId : "anonymous"));
        try {
            // 해시태그 추출
            List<String> hashtags = HashtagExtractor.extractHashtags(feedContent);
            logger.info("Extracted hashtags: " + hashtags);

            // 이미지 파일 저장 및 이름 수집
            List<String> imageNames = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                if (!imageFile.isEmpty()) {
                    String filePath = uploadDir + File.separator + imageFile.getOriginalFilename();
                    File destFile = new File(filePath);
                    try {
                        imageFile.transferTo(destFile);
                        imageNames.add(imageFile.getOriginalFilename());
                        logger.info("Saved image: " + imageFile.getOriginalFilename() + " at " + filePath);
                    } catch (IOException e) {
                        logger.severe("Failed to save image: " + imageFile.getOriginalFilename() + " due to " + e.getMessage());
                        throw e;
                    }
                }
            }

            // 피드 업데이트 및 데이터 저장
            Feed feed = new Feed();
            feed.setFeedId(feedId);
            feed.setUserId(userId != null ? userId : "anonymous");
            feed.setFeedContent(feedContent);

            feedCreateService.updateFeed(feed, hashtags, imageNames);
            logger.info("Feed successfully updated for user: " + (userId != null ? userId : "anonymous"));

            return "redirect:/modifyFeedSuccess";

        } catch (IOException e) {
            logger.severe("Error occurred while updating feed: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    @PostMapping("/feed/delete")
    public String deleteFeed(@RequestParam("feedId") int feedId) {
        logger.info("Received request to delete feed with ID: " + feedId);
        try {
            feedCreateService.deleteFeed(feedId);
            logger.info("Feed successfully deleted with ID: " + feedId);
            return "redirect:/deleteFeedSuccess";
        } catch (Exception e) {
            logger.severe("Error occurred while deleting feed: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
