package sample.frisorsalonjfx;

import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Database.DatabaseConnection;
import sample.frisorsalonjfx.Database.IKlippeTypeRepository;
import sample.frisorsalonjfx.Database.KlippetypeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class KlippeTypeLogic implements IKlippeType{

    private final IKlippeTypeRepository klippeTypeRepo;

    public KlippeTypeLogic(IKlippeTypeRepository klippeTypeRepo) {
        this.klippeTypeRepo = klippeTypeRepo;
    }
    @Override
    public Klippetype createKlippeType(String name, int pris, int tid) throws InputMismatchException {
        try {
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
