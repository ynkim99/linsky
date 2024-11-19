package app.labs.linksy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.labs.linksy.DAO.NewUserRepository;
import app.labs.linksy.Model.Member;

@Service
public class NewUserService {

    @Autowired
    private NewUserRepository newUserRepository;

    public NewUserService(NewUserRepository newUserRepository) {
        this.newUserRepository = newUserRepository;
    }

    public void registerMember(Member member) {
        newUserRepository.registerMember(member);
    }

    public boolean isUserIdDuplicate(String userId) {
        return newUserRepository.isUserIdDuplicate(userId);
    }

    
}
