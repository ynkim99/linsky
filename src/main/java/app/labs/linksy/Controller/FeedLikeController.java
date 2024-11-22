package app.labs.linksy.Controller;

import app.labs.linksy.DAO.FeedLikeMapper;
import app.labs.linksy.DAO.UserMapper;
import app.labs.linksy.Model.ExtraFeedLike;
import app.labs.linksy.Model.FeedLike;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.FeedLikeService;
import app.labs.linksy.Service.FeedService;
import app.labs.linksy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:80")
@Controller
@RequestMapping("/api/feed")
public class FeedLikeController {

    @Autowired
    private FeedLikeService feedLikeService;

    @Autowired
    private UserService userService; // UserService 주입

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FeedLikeMapper feedLikeMapper;

    // 특정 피드의 좋아요 리스트를 JSON으로 반환
    @GetMapping("/likes")
    @ResponseBody
    public List<ExtraFeedLike> getLikesByFeedId(int feedId) {
        // 1. FEED_LIKE 테이블에서 피드에 대한 좋아요 정보 가져오기
        List<FeedLike> feedLikes = feedLikeMapper.getLikesByFeedId(feedId);

        // 2. USER_ID 목록 생성
        List<String> userIds = feedLikes.stream()
                .map(FeedLike::getUserId)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        // 3. 한번에 USER_IMG를 가져오기
        List<Map<String, String>> userImages = userMapper.getUserImages(userIds);

        // 4. USER_ID와 USER_IMG 매핑
        Map<String, String> userImageMap = userImages.stream()
                .collect(Collectors.toMap(
                        img -> img.get("USER_ID"),
                        img -> img.get("USER_IMG"),
                        (existing, replacement) -> existing // 중복 발생 시 기존 값 유지
                ));

        // 5. ExtraFeedLike 리스트 생성
        List<ExtraFeedLike> extraFeedLikes = new ArrayList<>();
        for (FeedLike feedLike : feedLikes) {
            ExtraFeedLike extraFeedLike = new ExtraFeedLike();
            extraFeedLike.setUserFeedLikeId(feedLike.getUserFeedLikeId());
            extraFeedLike.setUserId(feedLike.getUserId());
            extraFeedLike.setFeedId(feedLike.getFeedId());

            // USER_IMG 설정
            String userImg = userImageMap.get(feedLike.getUserId());
            String fullImagePath = "/images/profile/" + userImg;
            extraFeedLike.setUserImg(fullImagePath);

            extraFeedLikes.add(extraFeedLike);
        }

        return extraFeedLikes;
    }



    // 특정 피드의 좋아요를 누른 사용자들의 프로필 리스트를 반환
    @GetMapping("/{feedId}/likes/profiles")
    public ResponseEntity<?> getLikeProfilesByFeedId(@PathVariable("feedId") int feedId) {
        try {
            List<Member> profiles = feedLikeService.getLikeProfilesByFeedId(feedId);
            if (profiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving profiles: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // HTML 페이지 반환
    @GetMapping("/likeslist")
    public String showLikesList() {
        return "likeslist"; // templates/likeslist.html 파일을 반환
    }

}