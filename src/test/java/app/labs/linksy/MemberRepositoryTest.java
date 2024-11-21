package app.labs.linksy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.labs.linksy.DAO.MemberRepository;
import app.labs.linksy.Model.Member;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
    private MemberRepository memberRepository;

    @Test
    public void testGetMemberByUserId() {
        String userId = "testUser";
        Member member = memberRepository.getMemberByUserId(userId);

        if (member == null) {
            System.out.println("No member found for userId: " + userId);
        } else {
            System.out.println("Member fetched: " + member);
        }
    }

}
