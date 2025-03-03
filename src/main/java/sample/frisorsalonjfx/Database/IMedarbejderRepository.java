package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Medarbejder;

public interface IMedarbejderRepository {

    void createMedarbejder(Medarbejder medarbejder);
    void deleteMedarbejder(Medarbejder medarbejder);
    ObservableList<Medarbejder> readMedarbejdere();

}
