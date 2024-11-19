package app.labs.linksy.Controller;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.NewUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewUserPageController {

    @Autowired
    private NewUserService newUserService;

    @GetMapping("/newuser")
    public String showNewUserPage() {
        return "newUserPage";
    }

    @PostMapping("/newuser")
    public String newUser(@RequestParam("userId") String userId,
                          @RequestParam("password") String password,
                          @RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("nickname") String nickname) {
        Member member = Member.builder()
                .userId(userId)
                .userPwd(password)
                .userName(name)
                .userEmail(email)
                .userNickname(nickname)
                .build();
                newUserService.registerMember(member);
        return "redirect:/login";
    }

    @GetMapping("/newuser/{userId}")
    public String duplicateCheck(@PathVariable("userId") String userId) {
        boolean isDuplicate = newUserService.isUserIdDuplicate(userId);
        return isDuplicate ? "duplicate" : "available";
    }
}
