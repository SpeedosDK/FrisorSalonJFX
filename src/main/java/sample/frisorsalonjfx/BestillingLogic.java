package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.IBestillingRepository;
import sample.frisorsalonjfx.Database.IKlippeTypeRepository;
import sample.frisorsalonjfx.Database.IKundeRepository;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;

import java.time.*;
import java.util.List;

public class BestillingLogic implements IBestillinger {

    private final IBestillingRepository bestillingRepo;
    private final IMedarbejderRepository medarbejderRepo;
    private final IKundeRepository kundeRepo;
    private final IKlippeTypeRepository klippeTypeRepo;


    public BestillingLogic(IBestillingRepository bestillingRepo, IMedarbejderRepository medarbejderRepo, IKlippeTypeRepository klippeTypeRepo, IKundeRepository kundeRepo) {
        this.bestillingRepo = bestillingRepo;
        this.medarbejderRepo = medarbejderRepo;
        this.kundeRepo = kundeRepo;
        this.klippeTypeRepo = klippeTypeRepo;
    }
//    @Override
//    public void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
//        bestillingRepo.createBestilling(new Bestilling(id, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype));
//    }
//
//    @Override
//    public boolean opretBestilling(Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
//        List<Bestilling> bestillinger = bestillingRepo.readBestillinger();
//        for (Bestilling b : bestillinger) {
//            if (bestilling_dato == b.getBestilling_dato() && medarbejder == b.getMedarbejder()) {
//                if (b.getBestilling_time() == bestilling_time) {
//                    return true;
//                }
//            } else {
//                nyBestilling(1, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype);
//                return false;
//            }
//        }
//        return false;
//    }


    @Override
    public boolean opretBestilling(int id, Medarbejder medarbejder, LocalDateTime date, LocalTime time, Kunde kunde, Klippetype klippetype) {
        if (isMedarbejderAvailable(medarbejder, date, time, klippetype)) {
            Bestilling bestilling = new Bestilling(id, medarbejder, date, time, kunde, klippetype);
            bestillingRepo.createBestilling(bestilling);
            return true;
        }
        return false;
    }

    @Override
    public boolean isMedarbejderAvailable(Medarbejder medarbejder, LocalDateTime date, LocalTime time, Klippetype klippetype) {
        List<Bestilling> exsitingBestillinger = bestillingRepo.readBestillingerByMedarbejder(medarbejder.getId());
        LocalDateTime requestedStart = date.with(time);
        LocalDateTime requestedEnd = requestedStart.plusMinutes(klippetype.getTimeForCut());

        for (Bestilling bestilling : exsitingBestillinger) {
            LocalDateTime bestillingStart = bestilling.getBestilling_dato().with(bestilling.getBestilling_time());
            LocalDateTime bestillingEnd = bestillingStart.plusMinutes(bestilling.getKlippetype().getTimeForCut());

            if (requestedStart.isBefore(bestillingEnd) && requestedEnd.isAfter(bestillingStart)) {
                return false;
            }
        }
        return true;
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
    @Override
    public List<Medarbejder> getMedarbejder() {
        return medarbejderRepo.readMedarbejdere();
    }
    @Override
    public List<Kunde> getKunde() {
        return kundeRepo.readKunder();
    }
    @Override
    public List<Klippetype> getKlippetype() {
        return klippeTypeRepo.readKlippeTyper();
    }
    @Override
    public List<LocalTime> getTimeAvailable() {
        return List.of(LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0));
    }

}
