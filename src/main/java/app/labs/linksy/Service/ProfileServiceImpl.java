package app.labs.linksy.Service;

import app.labs.linksy.DAO.ProfileDAO;
import app.labs.linksy.Model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    @Override
    public Profile getProfile(String id) {
        return profileDAO.getProfile(id);
    }

    @Override
    public void saveProfile(Profile profile) {
        profileDAO.saveProfile(profile);
    }
}
