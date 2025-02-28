package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

public interface IMedarbejder {
    Medarbejder addMedarbejder(String name, String password, boolean admin);
    ObservableList<Medarbejder> getAllMedarbejder();
    Medarbejder deleteMedarbejder(Medarbejder m);
}
