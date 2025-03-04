package sample.frisorsalonjfx;

import sample.frisorsalonjfx.Model.Medarbejder;

public class UserHolder {
    private static final UserHolder INSTANCE = new UserHolder();
    private Medarbejder currentUser;

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public Medarbejder getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Medarbejder currentUser) {
        this.currentUser = currentUser;
    }
}
