package app.labs.linksy.Service;

import app.labs.linksy.DAO.FeedLikeMapper;
import app.labs.linksy.Model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeMapper feedLikeMapper;

    public List<Member> getLikeProfilesByFeedId(int feedId) {
        return feedLikeMapper.getLikeProfiles(feedId);
    }
}
