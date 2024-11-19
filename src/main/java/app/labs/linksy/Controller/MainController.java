package app.labs.linksy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.labs.linksy.Model.Member;
import app.labs.linksy.Service.MemberService;

@Controller
@RequestMapping("/linksy")
public class MainController {
	
	@Autowired
    private MemberService memberService;
	
	// 메인 페이지 호출
	@GetMapping(value="")
	public String main(Model model) {
		
		 String userId = "testUser"; // DB에서 가져올 사용자 ID (하드코딩된 값)
		 System.out.println("Fetching member with userId: " + userId);
		 
	     // DB에서 사용자 정보 가져오기
	     Member member = memberService.getMemberByUserId(userId);
	     
	     if (member == null) {
	         System.out.println("No user found with ID: " + userId);
	     } else {
	         System.out.println("User found: " + member.toString());
	     }
	     
	     model.addAttribute("member", member);
		return "main";
	}

}
