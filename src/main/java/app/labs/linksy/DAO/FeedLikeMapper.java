package app.labs.linksy.DAO;

import app.labs.linksy.Model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedLikeMapper {

    @Select("""
        SELECT m.USER_ID, m.USER_IMG
        FROM FEED_LIKE fl
        JOIN MEMBER m ON fl.USER_ID = m.USER_ID
        WHERE fl.FEED_ID = #{feedId}
    """)
    List<Member> getLikeProfiles(@Param("feedId") int feedId);
}
