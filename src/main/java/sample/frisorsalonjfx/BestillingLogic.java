package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.BestillingRepo;
import sample.frisorsalonjfx.Database.IBestillingRepository;

import java.time.*;
import java.util.List;

public class BestillingLogic implements IBestillinger {

    private final IBestillingRepository bestillingRepo;


    public BestillingLogic(IBestillingRepository bestillingRepo) {
        this.bestillingRepo = bestillingRepo;
    }
    @Override
    public void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
        bestillingRepo.createBestilling(new Bestilling(id, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype));
    }

    @Override
    public boolean opretBestilling(Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
        List<Bestilling> bestillinger = bestillingRepo.readBestillinger();
        for (Bestilling b : bestillinger) {
            if (bestilling_dato == b.getBestilling_dato() && medarbejder == b.getMedarbejder()) {
                if (b.getBestilling_time() == bestilling_time) {
                    return true;
                }
            } else {
                nyBestilling(1, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype);
                return false;
            }
        }
        return false;
    }
    @Override
    public ObservableList<Bestilling> getBestillinger() {
        return bestillingRepo.readBestillinger();
    }
    @Override
    public Bestilling deleteBestilling(Bestilling b) {
        bestillingRepo.deleteBestilling(b);
        return b;
    }



}
