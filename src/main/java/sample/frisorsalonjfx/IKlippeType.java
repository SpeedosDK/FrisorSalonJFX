package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

public interface IKlippeType {
    Klippetype createKlippeType(String name, int pris, int tid);
    ObservableList<Klippetype> getKlippeTyper();
}
