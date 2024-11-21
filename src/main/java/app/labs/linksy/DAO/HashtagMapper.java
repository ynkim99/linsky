package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.*;

@Mapper // MyBatis 매퍼임을 명시적으로 선언
public interface HashtagMapper {

    // 해시태그가 이미 존재하는지 확인
    @Select("SELECT HASHTAG_ID FROM HASHTAG WHERE HASHTAG = #{hashtag}")
    Integer findHashtagIdByName(@Param("hashtag") String hashtag);

    // 존재하지 않는다면 새로 삽입
    @Insert("INSERT INTO HASHTAG (HASHTAG_ID, HASHTAG) VALUES (HASHTAG_SEQ.NEXTVAL, #{hashtag})")
    void insertHashtag(@Param("hashtag") String hashtag);

    // 게시물과 해시태그 관계 저장
    @Insert("INSERT INTO FEED_HASHTAG (FEED_ID, HASHTAG_ID) VALUES (#{feedId}, #{hashtagId})")
    void insertFeedHashtag(@Param("feedId") Integer feedId, @Param("hashtagId") Integer hashtagId);

    // 선택적: 중복 해시태그 방지를 위한 메서드
    @Select("SELECT COUNT(*) FROM FEED_HASHTAG WHERE FEED_ID = #{feedId} AND HASHTAG_ID = #{hashtagId}")
    int checkFeedHashtagExists(@Param("feedId") Integer feedId, @Param("hashtagId") Integer hashtagId);

    // 선택적: 피드-해시태그 관계 삭제 메서드 추가
    @Insert("DELETE FROM FEED_HASHTAG WHERE FEED_ID = #{feedId} AND HASHTAG_ID = #{hashtagId}")
    void deleteFeedHashtag(@Param("feedId") Integer feedId, @Param("hashtagId") Integer hashtagId);

    // 선택적: 피드-해시태그 관계 삭제 메서드 추가
    @Delete("DELETE FROM FEED_HASHTAG WHERE FEED_ID = #{feedId}")
    void deleteFeedHashtagsByFeedId(@Param("feedId") Integer feedId);
}
