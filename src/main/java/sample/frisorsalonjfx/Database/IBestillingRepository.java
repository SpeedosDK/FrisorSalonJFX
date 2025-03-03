package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Bestilling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IBestillingRepository {
    void createBestilling(Bestilling bestilling);
    ObservableList<Bestilling> readBestillinger();
    List<Bestilling> readBestillingerByMedarbejder(int medarbejderId);
    void deleteBestilling(Bestilling bestilling);

    boolean isMedarbejderAvailable(int medarbejderId, LocalDateTime bestillingDato, LocalTime bestillingTid);
}
