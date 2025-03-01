package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Bestilling;
import sample.frisorsalonjfx.Medarbejder;

import java.util.List;

public interface IBestillingRepository {
    void createBestilling(Bestilling bestilling);
    ObservableList<Bestilling> readBestillinger();
    List<Bestilling> readBestillingerByMedarbejder(int medarbejderId);
    void deleteBestilling(Bestilling bestilling);
}
