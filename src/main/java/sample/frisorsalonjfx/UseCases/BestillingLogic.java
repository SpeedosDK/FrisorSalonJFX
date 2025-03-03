package sample.frisorsalonjfx.UseCases;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.*;
import sample.frisorsalonjfx.Database.IBestillingRepository;
import sample.frisorsalonjfx.Database.IKlippeTypeRepository;
import sample.frisorsalonjfx.Database.IKundeRepository;
import sample.frisorsalonjfx.Database.IMedarbejderRepository;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;

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
        if (isMedarbejderAvailable(medarbejder, date, time)) {
            Bestilling bestilling = new Bestilling(id, medarbejder, date, time, kunde, klippetype);
            bestillingRepo.createBestilling(bestilling);
            return true;
        }
        return false;
    }

    @Override
    public boolean isMedarbejderAvailable(Medarbejder medarbejder, LocalDateTime date, LocalTime time) {
        return bestillingRepo.isMedarbejderAvailable(medarbejder.getId(), date, time);
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
