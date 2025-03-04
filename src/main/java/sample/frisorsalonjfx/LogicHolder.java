package sample.frisorsalonjfx;

import sample.frisorsalonjfx.UseCases.MedarbejderLogic;

public class LogicHolder {
        private static final LogicHolder INSTANCE = new LogicHolder();
        private MedarbejderLogic medarbejderLogic;

        private LogicHolder() {}

        public static LogicHolder getInstance() {
            return INSTANCE;
        }

        public MedarbejderLogic getMedarbejderLogic() {
            return medarbejderLogic;
        }

        public void setMedarbejderLogic(MedarbejderLogic medarbejderLogic) {
            this.medarbejderLogic = medarbejderLogic;
        }
    }
