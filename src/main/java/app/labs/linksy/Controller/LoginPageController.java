package app.labs.linksy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("userId")
public class LoginPageController {
	
	@Autowired
	private LoginService loginService;
	
    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, 
                        @RequestParam("password") String password,
                        HttpSession session) {
        Member member = Member.builder()
                .userId(userId)
                .userPwd(password)
                .build();
        loginService.login(member);
        session.setAttribute("userId", userId);
        return "redirect:/linksy";
    }

}
