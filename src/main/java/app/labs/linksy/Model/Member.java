package app.labs.linksy.Model;

public class Member {
    private String userNickname;
    private String userEmail;
    private String profileImage;

    // userNickname의 getter와 setter
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    // userEmail의 getter와 setter
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // profileImage의 getter와 setter
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
