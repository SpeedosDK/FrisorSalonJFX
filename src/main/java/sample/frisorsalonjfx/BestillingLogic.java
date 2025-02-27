package sample.frisorsalonjfx;

import javafx.scene.chart.PieChart;
import java.time.*;

public class BestillingLogic {

    DatabaseRepo db = new DatabaseRepo();
    public void nyBestilling(int id, Medarbejder medarbejder, LocalDateTime bestilling_dato, LocalTime bestilling_time, Kunde kunde, Klippetype klippetype) {
        db.createBestilling(new Bestilling(id, medarbejder, bestilling_dato, bestilling_time, kunde, klippetype));
    }



}
