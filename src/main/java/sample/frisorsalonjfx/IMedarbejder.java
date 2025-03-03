package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Medarbejder;

public interface IMedarbejder {
    void setCurrentUser(Medarbejder currentUser);

    Medarbejder addMedarbejder(String name, String password, boolean admin);
    ObservableList<Medarbejder> getAllMedarbejder();
    Medarbejder deleteMedarbejder(Medarbejder m);
}
