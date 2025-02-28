package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.BestillingRepo;
import sample.frisorsalonjfx.Database.IBestillingRepository;

import java.time.*;

public class BestillingLogic implements IBestillinger {

    DatabaseRepo db = new DatabaseRepo();
    public void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
        bestillingRepo.createBestilling(new Bestilling(id, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype));
    }



}
