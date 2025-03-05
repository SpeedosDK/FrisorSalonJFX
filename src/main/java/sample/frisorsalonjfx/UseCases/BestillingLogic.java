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
    public void updateBestilling(Bestilling bestilling) {
        bestillingRepo.updateBestilling(bestilling);
    }

    @Override
    public void deleteBestilling(Bestilling bestilling) {
        bestillingRepo.deleteBestilling(bestilling);
    }

    public ObservableList<Bestilling> findBestilling(String searchedName, String searchedMedarbejder, LocalDateTime searchedDate, Klippetype klippetype) {
        return bestillingRepo.searchBestilling(searchedName, searchedMedarbejder, searchedDate, klippetype);
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
