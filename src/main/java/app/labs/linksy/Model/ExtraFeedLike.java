package app.labs.linksy.Model;

//데이터 핸들릴용 오라클 DB에 저장 안됨//


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ExtraFeedLike {
    private int userFeedLikeId; // 좋아요 ID
    private String userId;      // 좋아요를 누른 사용자 ID
    private int feedId;         // 좋아요가 달린 피드 ID
    private String userImg;     // 사용자 프로필 이미지 경로

    // 기본 생성자
    public ExtraFeedLike() {
    }
}
