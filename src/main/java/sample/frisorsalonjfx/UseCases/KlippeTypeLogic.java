package sample.frisorsalonjfx.UseCases;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.IKlippeTypeRepository;
import sample.frisorsalonjfx.IKlippeType;
import sample.frisorsalonjfx.Model.Klippetype;

import java.util.InputMismatchException;

public class KlippeTypeLogic implements IKlippeType {

    private final IKlippeTypeRepository klippeTypeRepo;

    public KlippeTypeLogic(IKlippeTypeRepository klippeTypeRepo) {
        this.klippeTypeRepo = klippeTypeRepo;
    }
    @Override
    public Klippetype createKlippeType(String name, int pris, int tid) throws InputMismatchException {
        try {
            System.out.println("Laver klippe type: " + name + " " + pris + " " + tid);
            Klippetype klippetype = new Klippetype(1, name, pris, tid);
            klippeTypeRepo.createKlippeType(klippetype);
            return klippetype;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input");
        }
    }
    @Override
    public ObservableList<Klippetype> getKlippeTyper() {
        return klippeTypeRepo.readKlippeTyper();
    }
}
