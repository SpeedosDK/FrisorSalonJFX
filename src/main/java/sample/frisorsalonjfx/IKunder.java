package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

public interface IKunder {

    Kunde createKunde(String name, int tlf, String email);
    ObservableList<Kunde> getKunder();
    Kunde deleteKunde(Kunde kunde);
}
