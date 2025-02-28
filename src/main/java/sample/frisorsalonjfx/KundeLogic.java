package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.IKundeRepository;
import sample.frisorsalonjfx.Database.KundeRepo;

import java.util.InputMismatchException;

public class KundeLogic  implements IKunder{

    private final IKundeRepository kundeRepo;

    public KundeLogic(IKundeRepository kundeRepo) {
        this.kundeRepo = kundeRepo;
    }

    @Override
    public Kunde createKunde(String name, int tlf, String email) throws InputMismatchException {
        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Navn kan ikke være tomt");
            }
            Kunde kunde = new Kunde(1, name, tlf, email);
            kundeRepo.createKunde(kunde);
            return kunde;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input");
        }
    }
    @Override
    public ObservableList<Kunde> getKunder() {
        return kundeRepo.readKunder();
    }
    @Override
    public Kunde deleteKunde(Kunde k) {
        kundeRepo.deleteKunde(k);
        return k;
    }
}
