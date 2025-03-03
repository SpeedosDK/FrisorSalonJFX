package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Kunde;

public interface IKundeRepository {

    void createKunde(Kunde kunde);
    ObservableList<Kunde> readKunder();
    void deleteKunde(Kunde kunde);
}
