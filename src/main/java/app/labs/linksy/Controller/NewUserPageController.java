package app.labs.linksy.Controller;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.NewUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

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
                       @RequestParam("nickname") String nickname,
                       Model model) {
        try {   
            Member member = Member.builder()
                    .userId(userId)
                    .userPwd(password)
                    .userName(name)
                    .userEmail(email)
                    .userNickname(nickname)
                    .build();
            newUserService.registerMember(member);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입에 실패하였습니다.");
            return "newUserPage";
        }
    }

    @GetMapping("/newuser/{userId}")
    @ResponseBody
    public Map<String, String> duplicateCheck(@PathVariable("userId") String userId) {
        boolean isDuplicate = newUserService.isUserIdDuplicate(userId);
        Map<String, String> response = new HashMap<>();
        response.put("data", isDuplicate ? "duplicate" : "available" );
        return response;
    }
}
