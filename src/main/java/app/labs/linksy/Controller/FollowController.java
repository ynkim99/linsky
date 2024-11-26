package app.labs.linksy.Controller;

import app.labs.linksy.Service.FollowService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequiredArgsConstructor
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/api/follow/check/{userId}")
    @ResponseBody
    public ResponseEntity<Boolean> checkFollowStatus(
        @PathVariable("userId") String userId,
        HttpSession session
    ) {
        try {
            String getUserId = (String) session.getAttribute("userId");
            boolean result = followService.checkFollowStatus(getUserId, userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/api/follow")
    @ResponseBody
    public ResponseEntity<String> follow(
        @RequestParam("followingId") String followingId,
        @RequestParam("followerId") String followerId
    ){
        try {
            System.out.println("Following ID: " + followingId);
            System.out.println("Follower ID: " + followerId);
            followService.follow(followingId, followerId);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/api/unfollow")
    @ResponseBody
    public ResponseEntity<String> unfollow(
        @RequestParam("followingId") String followingId,
        @RequestParam("followerId") String followerId
    ){
        try {
            System.out.println("Unfollowing ID: " + followingId);
            System.out.println("Follower ID: " + followerId);
            followService.unfollow(followingId, followerId);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
