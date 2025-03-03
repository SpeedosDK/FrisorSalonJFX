package sample.frisorsalonjfx.UseCases;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.IAuthService;
import sample.frisorsalonjfx.IMedarbejder;
import sample.frisorsalonjfx.LoginException;
import sample.frisorsalonjfx.Model.Medarbejder;

import java.util.List;

public class LoginLogic implements IAuthService {

    IMedarbejderRepository medarbejderRepo;
    MedarbejderLogic medarbejderLogic;

    public LoginLogic(IMedarbejderRepository medarbejderRepo, MedarbejderLogic medarbejderLogic) {
        this.medarbejderRepo = medarbejderRepo;
        this.medarbejderLogic = medarbejderLogic;

    }

    @Override
    public Medarbejder login(String username, String password) throws LoginException {
        List<Medarbejder> medarbejdere = medarbejderRepo.readMedarbejdere();
        try {
            for (Medarbejder medarbejder : medarbejdere) {
                System.out.println(medarbejdere);
                System.out.println(medarbejder.isAdmin());
                if (username.equals(medarbejder.getName())) {
                    if (password.equals(medarbejder.getPassword())) {
                        medarbejderLogic.setCurrentUser(medarbejder);
                        System.out.println("Current user efter logget ind" + medarbejderLogic.getCurrentUser());
                        System.out.println(medarbejder.getName());
                        return medarbejder;
                    }
                }
            }
        } catch (LoginException e) {
            throw new LoginException("Login fejlet");
        }
        return null;
    }
    @Override
    public MedarbejderLogic getMedarbejderLogic() {
        return medarbejderLogic;
    }





}
