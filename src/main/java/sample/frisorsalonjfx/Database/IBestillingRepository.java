package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Bestilling;

public interface IBestillingRepository {
    void createBestilling(Bestilling bestilling);
    ObservableList<Bestilling> readBestillinger();
    void deleteBestilling(Bestilling bestilling);
}
