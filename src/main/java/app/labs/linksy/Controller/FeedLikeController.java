package app.labs.linksy.Controller;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:80")
@Controller
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

    @GetMapping("/likeslist")
    public String showLikesList() {
        return "likeslist"; // likeslist.html 파일을 반환
    }

    @GetMapping("/likes")
    @ResponseBody
    public List<Member> getLikesList(@RequestParam("feedId") int feedId) {
        return feedLikeService.getLikeProfilesByFeedId(feedId);
    }

}
