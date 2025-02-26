package sample.frisorsalonjfx;

public class Medarbejder implements Person {
    private int id;
    private String name;
    private String password;

    public Medarbejder(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }



}
