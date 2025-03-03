package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Kunde;

public interface IKunder {

    Kunde createKunde(String name, int tlf, String email);
    ObservableList<Kunde> getKunder();
    Kunde deleteKunde(Kunde kunde);
}
