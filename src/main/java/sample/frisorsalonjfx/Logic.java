package sample.frisorsalonjfx;
import java.util.List;

public class Logic {

    public static boolean login(String username, String password) throws LoginException {
        List<Medarbejder> medarbejdere = DatabaseRepo.readMedarbejdere();

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
