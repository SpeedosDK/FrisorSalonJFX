package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

import java.util.InputMismatchException;

public class KundeLogic {

    public DatabaseRepo db = new DatabaseRepo();

    public Kunde createKunde(String name, int tlf, String email) throws InputMismatchException {
        try {
            Kunde kunde = new Kunde(1, name, tlf, email);
            db.createKunde(kunde);
            return kunde;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input");
        }

    }

    public ObservableList<Kunde> getKunder() {
        return db.readKunder();
    }
}
