package app.labs.linksy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("logout");
        session.removeAttribute("userId");
        session.invalidate();
        return "redirect:/login";
    }
}
