package app.labs.linksy.DAO;

import org.apache.ibatis.annotations.Mapper;

import app.labs.linksy.Model.Member;

@Mapper
public interface NewUserRepository {
	void registerMember(Member member);
	boolean isUserIdDuplicate(String userId);
}
