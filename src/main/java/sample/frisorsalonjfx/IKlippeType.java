package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Klippetype;

public interface IKlippeType {
    Klippetype createKlippeType(String name, int pris, int tid);
    ObservableList<Klippetype> getKlippeTyper();
}
