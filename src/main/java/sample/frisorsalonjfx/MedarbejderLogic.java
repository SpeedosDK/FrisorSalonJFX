package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

public class MedarbejderLogic {

    DatabaseRepo db = new DatabaseRepo();
    public Medarbejder addMedarbejder(String name, String password) {
        Medarbejder medarbejder = new Medarbejder(1, name, password);
        db.createMedarbejder(medarbejder);
        return medarbejder;
    }

    public ObservableList<Medarbejder> getAllMedarbejder() {
        return db.readMedarbejdere();
    }
}
