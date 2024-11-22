package app.labs.linksy.Service;

import app.labs.linksy.DAO.FeedLikeMapper;
import app.labs.linksy.DAO.UserMapper;
import app.labs.linksy.DAO.testingrepository;
import app.labs.linksy.Model.ExtraFeedLike;
import app.labs.linksy.Model.FeedLike;
import app.labs.linksy.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeedLikeService {

    @Autowired
    private FeedLikeMapper feedLikeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private testingrepository repository;

    public List<ExtraFeedLike> getLikesByFeedId(int feedId) {
        // 1. FEED_LIKE 테이블에서 피드에 대한 좋아요 정보 가져오기
        List<FeedLike> feedLikes = feedLikeMapper.getLikesByFeedId(feedId);

        // 2. USER_ID 목록 생성
        List<String> userIds = feedLikes.stream()
                .map(FeedLike::getUserId)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        System.out.println("User IDs: " + userIds);

        // 3. 한번에 USER_IMG를 가져오기
        List<Map<String, String>> userImages = userMapper.getUserImages(userIds);

        System.out.println("Returned User Images: " + userImages);

        // 4. USER_ID와 USER_IMG 매핑
        Map<String, String> userImageMap = userImages.stream()
                .collect(Collectors.toMap(
                        img -> img.get("USER_ID"),
                        img -> img.get("USER_IMG"),
                        (existing, replacement) -> existing // 중복 발생 시 기존 값 유지
                ));

        System.out.println("User Image Map: " + userImageMap);
        List<Map<String, String>> Map = userMapper.getUserImages(userIds);
        System.out.println("Fetched User Images: " + Map);


        // 5. ExtraFeedLike 리스트 생성
        List<ExtraFeedLike> extraFeedLikes = new ArrayList<>();
        for (FeedLike feedLike : feedLikes) {
            ExtraFeedLike extraFeedLike = new ExtraFeedLike();
            extraFeedLike.setUserFeedLikeId(feedLike.getUserFeedLikeId());
            extraFeedLike.setUserId(feedLike.getUserId());
            extraFeedLike.setFeedId(feedLike.getFeedId());

            // USER_IMG 설정
            System.out.println("FeedLike User ID: " + feedLike.getUserId());
            String userImg = userImageMap.get(feedLike.getUserId());
            if (userImg == null) {
                System.out.println("No image found for User ID: " + feedLike.getUserId());
                userImg = "default.png"; // 기본 이미지 설정
            }
            String fullImagePath = "/images/profile/" + userImg;
            extraFeedLike.setUserImg(fullImagePath);

            extraFeedLikes.add(extraFeedLike);
        }

        return extraFeedLikes;
    }




    // 특정 피드에 좋아요를 누른 사용자들의 프로필 리스트를 반환
    public List<Member> getLikeProfilesByFeedId(int feedId) {
        return feedLikeMapper.getLikeProfilesByFeedId(feedId);
    }
}