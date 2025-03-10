package sample.frisorsalonjfx.UseCases;

import javafx.collections.FXCollections;
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
    public boolean opretBestilling(int id, Medarbejder medarbejder, LocalDateTime date, LocalTime time, Kunde kunde, Klippetype klippetype, boolean aktiv) {
        if (isMedarbejderAvailable(medarbejder, date, time)) {
            Bestilling bestilling = new Bestilling(id, medarbejder, date, time, kunde, klippetype, aktiv);
            bestillingRepo.createBestilling(bestilling);
            return true;
        }
        return false;
    }

    @Override // Denne metode tjekker om en bestilling er over 5 år gammel hver gang man åbner listen med bestillinger
    // (hvilket man kan regne med bliver gjort dagligt) og sletter de bestillinger der er for gamle
    // Aner seriøst ikke hvor den her metode burde være men føler ikke at det er her. Nu er den her tho ¯\_(ツ)_/¯
    public void sletGamleBestillinger() {
        List<Bestilling> bestillinger = bestillingRepo.readBestillinger();

        LocalDateTime nuTid = LocalDateTime.now();
        LocalDateTime fiveYearsAgo = nuTid.minusYears(5);

        for (Bestilling bestilling : bestillinger) {
       		if (bestilling.getBestilling_dato().isBefore(fiveYearsAgo)) {
       			deleteBestilling(bestilling);
       		}
      	}
    }

    @Override
    // tjekker om bestillingerne er før idag, og hvis ja, sætter dem som inaktiv.
    public void setGamleBestillingerInaktive(List<Bestilling> bestillinger) {
        for (Bestilling bestilling : bestillinger) {
            if (bestilling.getBestilling_dato().isBefore(LocalDateTime.now().minusDays(1))) {
                bestilling.setAktiv(false);
                updateBestilling(bestilling);
            }
        }
    }

    @Override // tjekker om medarbejderen har en bestilling på det tidspunkt
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
    public void deleteBestilling(Bestilling b) {
        bestillingRepo.deleteBestilling(b);
    }

    public ObservableList<Bestilling> findBestilling(String searchedName, String searchedMedarbejder, LocalDateTime searchedDate, Klippetype klippetype) {
        return bestillingRepo.searchBestilling(searchedName, searchedMedarbejder, searchedDate, klippetype);
    }

    @Override
    public List<Medarbejder> getMedarbejdere() {
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
    public ObservableList<LocalTime> getTimeAvailable() { // tilføjer tider så alle halve timer er en mulighed
        ObservableList<LocalTime> timeOptions = FXCollections.observableArrayList();
        for (int hour = 9; hour < 17; hour++) {
            timeOptions.add(LocalTime.of(hour, 0));
            timeOptions.add(LocalTime.of(hour, 30));
        }
        return timeOptions;
    }

}
