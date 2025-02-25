package sample.frisorsalonjfx;
import java.sql.*;
import java.util.*;


public class DatabaseRepo {

    public static List<Medarbejder> readMedarbejdere() {
        List<Medarbejder> medarbejdere = new ArrayList<>();
        String sql = "SELECT * FROM medarbejdere";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password;");
                medarbejdere.add(new Medarbejder(id, name, password));
            }
        } catch(SQLException e) {
        e.printStackTrace();
        }
        return medarbejdere;
    }


}



}





