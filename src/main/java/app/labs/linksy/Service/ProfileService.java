package app.labs.linksy.Service;

import app.labs.linksy.Model.Profile;

public interface ProfileService {
    Profile getProfile(String id);
    void saveProfile(Profile profile);
}
