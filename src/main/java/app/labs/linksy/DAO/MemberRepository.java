package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.labs.linksy.Model.Member;

@Mapper
public interface MemberRepository {
	 Member getMemberByUserId(@Param("userId") String userId);
}
