package sample.frisorsalonjfx;
import java.time.LocalDateTime;

public class Bestilling {
    private int id;
    private int medarbejder_id;
    private LocalDateTime bestilling_tid;
    private int kunde_id;
    private int klippetype_id;


    public Bestilling(int id, int medarbejder_id, LocalDateTime bestilling_tid, int kunde_id, int klippetype_id) {
        this.id = id;
        this.medarbejder_id = medarbejder_id;
        this.bestilling_tid = bestilling_tid;
        this.kunde_id = kunde_id;
        this.klippetype_id = klippetype_id;
    }


    public int getId() {
    return id;
    }


    public void setId(int id) {
    this.id = id;
    }

    public int getMedarbejder_id() {
        return medarbejder_id;
    }

    public void setMedarbejder_id(int medarbejder_id) {
        this.medarbejder_id = medarbejder_id;
    }

    public LocalDateTime getBestilling_tid() {
        return bestilling_tid;
    }

    public void setBestilling_tid(LocalDateTime bestilling_tid) {
        this.bestilling_tid = bestilling_tid;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public int getKlippetype_id() {
        return klippetype_id;
    }

    public void setKlippetype_id(int klippetype_id) {
        this.klippetype_id = klippetype_id;
    }



}
