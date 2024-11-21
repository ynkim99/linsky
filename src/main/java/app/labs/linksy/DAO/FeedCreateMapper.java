package app.labs.linksy.DAO;

import app.labs.linksy.Model.Feed;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedCreateMapper {

    // 피드 추가
    //@Insert("INSERT INTO FEED (USER_ID, FEED_CONTENT, FEED_TIME, LIKE_AMOUNT) VALUES (#{userId}, #{feedContent}, CURRENT_TIMESTAMP, #{likeAmount})")
    //@Options(useGeneratedKeys = true, keyProperty = "feedId", keyColumn = "FEED_ID")
    //void insertFeed(Feed feed);

    @Insert("INSERT INTO FEED (FEED_ID, USER_ID, FEED_CONTENT, FEED_TIME, LIKE_AMOUNT) " +
            "VALUES (FEED_SEQ.NEXTVAL, #{userId}, #{feedContent}, CURRENT_TIMESTAMP, #{likeAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "feedId", keyColumn = "FEED_ID")
    void insertFeed(Feed feed);


    // 해시태그 추가 후 ID 반환
    @Insert("INSERT INTO HASHTAG (HASHTAG) VALUES (#{hashtag})")
    @Options(useGeneratedKeys = true, keyProperty = "hashtagId", keyColumn = "HASHTAG_ID")
    int insertHashtagAndReturnId(String hashtag);

    // 피드-해시태그 관계 추가
    @Insert("INSERT INTO FEED_HASHTAG (FEED_ID, HASHTAG_ID) VALUES (#{feedId}, #{hashtagId})")
    void insertFeedHashtag(@Param("feedId") int feedId, @Param("hashtagId") int hashtagId);

    // 이미지 추가
    //@Insert("INSERT INTO FEED_IMAGE (FEED_ID, IMG_NAME) VALUES (#{feedId}, #{imgName})")
    //void insertFeedImage(@Param("feedId") int feedId, @Param("imgName") String imgName);

    @Insert("INSERT INTO FEED_IMAGE (IMAGE_ID, FEED_ID, IMG_NAME) VALUES (FEED_IMAGE_SEQ.NEXTVAL, #{feedId}, #{imgName})")
    void insertFeedImage(@Param("feedId") int feedId, @Param("imgName") String imgName);

    // 피드 업데이트
    @Update("UPDATE FEED SET FEED_CONTENT = #{feedContent}, FEED_TIME = CURRENT_TIMESTAMP WHERE FEED_ID = #{feedId}")
    void updateFeed(Feed feed);

    // 피드 삭제
    @Delete("DELETE FROM FEED WHERE FEED_ID = #{feedId}")
    void deleteFeed(int feedId);

    // 피드 해시태그 삭제
    @Delete("DELETE FROM FEED_HASHTAG WHERE FEED_ID = #{feedId}")
    void deleteFeedHashtags(int feedId);

    // 피드 이미지 삭제
    @Delete("DELETE FROM FEED_IMAGE WHERE FEED_ID = #{feedId}")
    void deleteFeedImages(int feedId);

    // 피드 조회
    @Select("SELECT * FROM FEED WHERE FEED_ID = #{feedId}")
    @Results({
            @Result(property = "feedId", column = "FEED_ID"),
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "feedContent", column = "FEED_CONTENT"),
            @Result(property = "feedTime", column = "FEED_TIME"),
            @Result(property = "likeAmount", column = "LIKE_AMOUNT")
    })
    Feed findFeedById(int feedId);
}
