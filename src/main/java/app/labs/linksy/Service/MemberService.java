package app.labs.linksy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.labs.linksy.DAO.MemberRepository;
import app.labs.linksy.Model.Member;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
    private MemberRepository memberRepository;
	
	@Override
	public Member getMemberByUserId(String userId) {
		 System.out.println("Querying DB for userId: " + userId);
		 Member member = memberRepository.getMemberByUserId(userId);
		
		 if (member == null) {
	            System.out.println("No member found for userId: " + userId);
	        } else {
	            System.out.println("Member found: " + member.getUserNickname());
	        }
		
		return member;
	}

}
