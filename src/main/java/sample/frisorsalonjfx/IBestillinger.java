package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IBestillinger {

    
    ObservableList<Bestilling> getBestillinger();
    void deleteBestilling(Bestilling bestilling);
    void updateBestilling(Bestilling bestilling);
    ObservableList<Bestilling> findBestilling(String searchedName, String searchedMedarbejder, LocalDateTime searchedDate, Klippetype klippetype);
    boolean opretBestilling(int id, Medarbejder medarbejder, LocalDateTime date, LocalTime time, Kunde kunde, Klippetype klippetype, boolean aktiv);
    boolean isMedarbejderAvailable(Medarbejder medarbejder, LocalDateTime date, LocalTime time);
    List<Medarbejder> getMedarbejdere();
    List<Kunde> getKunde();
    List<Klippetype> getKlippetype();
    List<LocalTime> getTimeAvailable();
    void sletGamleBestillinger();
    void setGamleBestillingerInaktive(List<Bestilling> bestillinger);

}
