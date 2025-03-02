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
    //void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype);
//    boolean opretBestilling(Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype);
    ObservableList<Bestilling> getBestillinger();
    Bestilling deleteBestilling(Bestilling bestilling);

    boolean opretBestilling(int id, Medarbejder medarbejder, LocalDateTime date, LocalTime time, Kunde kunde, Klippetype klippetype);

    boolean isMedarbejderAvailable(Medarbejder medarbejder, LocalDateTime date, LocalTime time);
    List<Medarbejder> getMedarbejder();
    List<Kunde> getKunde();
    List<Klippetype> getKlippetype();
    List<LocalTime> getTimeAvailable();
}
