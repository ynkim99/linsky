package app.labs.linksy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.labs.linksy.DAO.FollowRepository;
import app.labs.linksy.Model.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {
	@Autowired
    private FollowRepository followrepository;
    
    // 팔로우한 사용자 정보 가져오기
    public List<Member> getFollowings(String followerId) {
        return followrepository.getFollowings(followerId);
    }

    public int getFollowerCount(String userId) {
        return followrepository.getFollowerCount(userId);
    }

    public int getFollowingCount(String userId) {
        return followrepository.getFollowingCount(userId);
    }

    @Transactional
    public boolean checkFollowStatus(String followerId, String followingId) {
        return followrepository.checkFollowStatus(followerId, followingId);
    }

    @Transactional
    public void follow(String followingId, String followerId) {
        followrepository.follow(followingId, followerId);
    }

    @Transactional
    public void unfollow(String followingId, String followerId) {
        followrepository.unfollow(followingId, followerId);
    }
}
