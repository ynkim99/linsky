package app.labs.linksy.DAO;

import app.labs.linksy.Model.FeedLike;
import app.labs.linksy.Model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedLikeMapper {

    // 특정 피드에 좋아요를 누른 사용자들의 프로필 리스트를 가져옴
    @Select("SELECT m.USER_ID, m.USER_NAME, m.USER_IMG FROM FEED_LIKE fl JOIN MEMBER m ON fl.USER_ID = m.USER_ID WHERE fl.FEED_ID = #{feedId}")
    List<Member> getLikeProfilesByFeedId(@Param("feedId") int feedId);

    // 특정 피드의 좋아요 정보를 가져오는 메서드
    @Select("SELECT * FROM FEED_LIKE WHERE FEED_ID = #{feedId}")
    List<FeedLike> getLikesByFeedId(@Param("feedId") int feedId);

}

