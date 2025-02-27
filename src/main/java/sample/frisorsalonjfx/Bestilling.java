package sample.frisorsalonjfx;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Bestilling {
    private int id;
    private Medarbejder medarbejder;
    private LocalDateTime bestilling_dato;
    private LocalTime bestilling_time;
    private Kunde kunde;
    private Klippetype klippetype;


    public Bestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
        this.id = id;
        this.medarbejder = medarbejder;
        this.bestilling_dato = bestilling_dato;
        this.bestilling_time = bestilling_time;
        this.kunde = kunde;
        this.klippetype = klippetype;
    }


    public int getId() {
    return id;
    }


    public void setId(int id) {
    this.id = id;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public LocalDateTime getBestilling_dato() {
        return bestilling_dato;
    }

    public void setBestilling_dato(LocalDateTime bestilling_dato) {
        this.bestilling_dato = bestilling_dato;
    }

    public void setBestilling_time(LocalTime bestilling_time) {
        this.bestilling_time = bestilling_time;
    }

    public LocalTime getBestilling_time() {
        return bestilling_time;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Klippetype getKlippetype() {
        return klippetype;
    }

    public void setKlippetype(Klippetype klippetype) {
        this.klippetype = klippetype;
    }



}
