package sample.frisorsalonjfx;

import sample.frisorsalonjfx.Model.Medarbejder;
import sample.frisorsalonjfx.UseCases.MedarbejderLogic;

public interface IAuthService {
    Medarbejder login(String username, String password);
    MedarbejderLogic getMedarbejderLogic();
}
