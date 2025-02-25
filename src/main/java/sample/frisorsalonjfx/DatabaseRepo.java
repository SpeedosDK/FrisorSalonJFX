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

    public static void createBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO bestillinger (id, medarbejder_id, bestilling_tid, kunde_id, klippetype_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bestilling.getId());
            preparedStatement.setInt(2, bestilling.getMedarbejder_id());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(bestilling.getBestilling_tid()));
            preparedStatement.setInt(4, bestilling.getKunde_id());
            preparedStatement.setInt(5, bestilling.getKlippetype_id());
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Bestilling tilføjet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createKunde(Kunde kunde) {
        String sql = "INSERT INTO kunder (id, kunde_navn, kunde_telefon, kunde_email) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, kunde.getId());
            preparedStatement.setString(2, kunde.getName());
            preparedStatement.setInt(3, kunde.getTelefon());
            preparedStatement.setString(4, kunde.getEmail());
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Kunde oprettet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}



}





