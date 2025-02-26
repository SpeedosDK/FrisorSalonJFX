package sample.frisorsalonjfx;

public class Kunde implements Person {
    private int id;
    private String name;
    private int telefon;
    private String email;

    public Kunde(int id, String name, int telefon, String email) {
        this.id = id;
        this.name = name;
        this.telefon = telefon;
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
