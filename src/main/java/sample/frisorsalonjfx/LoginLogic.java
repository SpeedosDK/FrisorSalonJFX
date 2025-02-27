package sample.frisorsalonjfx;
import java.util.List;

public class LoginLogic {

    DatabaseRepo db = new DatabaseRepo();

    public boolean login(String username, String password) throws LoginException {
        List<Medarbejder> medarbejdere = db.readMedarbejdere();
        try {
            for (Medarbejder medarbejder : medarbejdere) {
                if (username.equals(medarbejder.getName())) {
                    if (password.equals(medarbejder.getPassword())) {
                        return true;
                    }
                }
            }
        } catch (LoginException e) {
            throw new LoginException("Login fejlet");
        }
        return false;
    }



}
