package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Klippetype;

public interface IKlippeTypeRepository {

    void createKlippeType(Klippetype klippetype);
    ObservableList<Klippetype> readKlippeTyper();
    void deleteKlippeType(Klippetype klippetype);
}
