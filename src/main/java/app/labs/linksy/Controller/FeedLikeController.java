package app.labs.linksy.Controller;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:80")
@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @GetMapping("/{feedId}/likes")
    public ResponseEntity<List<Member>> getLikeProfiles(@PathVariable("feedId") int feedId) {
        List<Member> profiles = feedLikeService.getLikeProfilesByFeedId(feedId);

        if (profiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profiles);
    }
}
