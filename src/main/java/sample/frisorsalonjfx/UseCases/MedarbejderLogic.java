package sample.frisorsalonjfx.UseCases;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.IAuthService;
import sample.frisorsalonjfx.IMedarbejder;
import sample.frisorsalonjfx.Model.Medarbejder;

public class MedarbejderLogic implements IMedarbejder {

    private final IMedarbejderRepository medarbejderRepo;
    Medarbejder currentUser;


    public MedarbejderLogic(IMedarbejderRepository medarbejderRepo) {
        this.medarbejderRepo = medarbejderRepo;
    }

    @Override
    public void setCurrentUser(Medarbejder currentUser) {
        this.currentUser = currentUser;
    }
    public Medarbejder getCurrentUser() {
        return currentUser;
    }
    private boolean isAdmin(Medarbejder currentUser) {
        System.out.println(currentUser.isAdmin());
        return currentUser != null && currentUser.isAdmin();
    }

    @Override
    public Medarbejder addMedarbejder(String name, String password, boolean admin) throws SecurityException {
        if (!isAdmin(currentUser)) {
            throw new SecurityException("Du skal være admin for at lave ny medarbejder");
        }
        Medarbejder medarbejder = new Medarbejder(1, name, password, admin);
        medarbejderRepo.createMedarbejder(medarbejder);
        return medarbejder;
    }

    @Override
    public ObservableList<Medarbejder> getAllMedarbejder() {
        return medarbejderRepo.readMedarbejdere();
    }
    @Override
    public Medarbejder deleteMedarbejder(Medarbejder medarbejder) throws SecurityException {

        if (!isAdmin(getCurrentUser())) {
            throw new SecurityException("Du skal være admin for at slette medarbejder");
        }
        medarbejderRepo.deleteMedarbejder(medarbejder);
        return medarbejder;
    }
}
