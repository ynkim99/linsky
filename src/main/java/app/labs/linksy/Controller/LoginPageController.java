package app.labs.linksy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.LoginService;

@Controller
public class LoginPageController {
	
	@Autowired
	private LoginService loginService;
	
    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        Member member = Member.builder()
                .userId(userId)
                .userPwd(password)
                .build();
        loginService.login(member);
        return "redirect:/";
    }

}
