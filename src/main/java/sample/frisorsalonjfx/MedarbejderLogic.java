package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.Database.MedarbejderRepo;

public class MedarbejderLogic implements IMedarbejder{

    private final IMedarbejderRepository medarbejderRepo;
    Medarbejder currentUser;


    public MedarbejderLogic(IMedarbejderRepository medarbejderRepo) {
        this.medarbejderRepo = medarbejderRepo;
    }
    private void setCurrentUser(Medarbejder currentUser) {
        this.currentUser = currentUser;
    }
    private boolean isAdmin(Medarbejder currentUser) {
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

        if (!isAdmin(currentUser)) {
            throw new SecurityException("Du skal være admin for at slette medarbejder");
        }
        medarbejderRepo.deleteMedarbejder(medarbejder);
        return medarbejder;
    }
}
