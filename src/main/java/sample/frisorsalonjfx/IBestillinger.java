package sample.frisorsalonjfx;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IBestillinger {
    void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype);
    boolean opretBestilling(Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype);
    ObservableList<Bestilling> getBestillinger();
    Bestilling deleteBestilling(Bestilling bestilling);
}
