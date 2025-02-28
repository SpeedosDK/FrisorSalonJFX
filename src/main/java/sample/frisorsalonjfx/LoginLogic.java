package sample.frisorsalonjfx;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.Database.MedarbejderRepo;

import java.util.List;

public class LoginLogic implements IAuthService {

    IMedarbejderRepository medarbejderRepo;

    public LoginLogic(IMedarbejderRepository medarbejderRepo) {
        this.medarbejderRepo = medarbejderRepo;
    }

    @Override
    public boolean login(String username, String password) throws LoginException {
        List<Medarbejder> medarbejdere = medarbejderRepo.readMedarbejdere();
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
