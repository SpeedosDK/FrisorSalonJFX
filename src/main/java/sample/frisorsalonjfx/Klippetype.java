package sample.frisorsalonjfx;

public class Klippetype {
    private int id;
    private String klippeStil;
    private int timeForCut;
    private int price;

    public Klippetype(int id, String klippeStil, int price, int timeForCut) {
        this.id = id;
        this.klippeStil = klippeStil;
        this.price = price;
        this.timeForCut = timeForCut;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKlippeStil() {
        return klippeStil;
    }
    public void setKlippeStil(String klippeStil) {
        this.klippeStil = klippeStil;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getTimeForCut() {
        return timeForCut;
    }
    public void setTimeForCut(int timeForCut) {
        this.timeForCut = timeForCut;
    }

    public String getName() {
        return klippeStil;
    }
    @Override
    public String toString() {
        return klippeStil;
    }
}
