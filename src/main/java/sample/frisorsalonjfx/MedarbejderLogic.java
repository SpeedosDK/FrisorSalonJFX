package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

public class MedarbejderLogic {

    DatabaseRepo db = new DatabaseRepo();
    Medarbejder currentUser;

    private void setCurrentUser(Medarbejder currentUser) {
        this.currentUser = currentUser;
    }
    private boolean isAdmin(Medarbejder currentUser) {
        return currentUser != null && currentUser.isAdmin();
    }
    public Medarbejder addMedarbejder(String name, String password, boolean admin) throws SecurityException {

        if (!isAdmin(currentUser)) {
            throw new SecurityException("Du skal være admin for at lave ny medarbejder");
        }
        Medarbejder medarbejder = new Medarbejder(1, name, password, admin);
        db.createMedarbejder(medarbejder);
        return medarbejder;

    }

    public ObservableList<Medarbejder> getAllMedarbejder() {
        return db.readMedarbejdere();
    }
}
