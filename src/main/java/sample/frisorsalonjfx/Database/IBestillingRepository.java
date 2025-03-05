package sample.frisorsalonjfx.Database;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IBestillingRepository {
    void createBestilling(Bestilling bestilling);
    ObservableList<Bestilling> readBestillinger();
    List<Bestilling> readBestillingerByMedarbejder(int medarbejderId);
    void updateBestilling(Bestilling bestilling);
    void deleteBestilling(Bestilling bestilling);
    ObservableList<Bestilling> searchBestilling(String searchedName, String searchedMedarbejder, LocalDateTime searchedDate, Klippetype klippetype);
    boolean isMedarbejderAvailable(int medarbejderId, LocalDateTime bestillingDato, LocalTime bestillingTid);

}
