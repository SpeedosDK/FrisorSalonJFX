package sample.frisorsalonjfx;
import java.sql.*;
import java.util.*;


public class DatabaseRepo {

    public List<Medarbejder> readMedarbejdere() {
        List<Medarbejder> medarbejdere = new ArrayList<>();
        String sql = "SELECT * FROM medarbejder";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("medarbejder_id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                medarbejdere.add(new Medarbejder(id, name, password));
            }
        } catch(SQLException e) {
        e.printStackTrace();
        }
        return medarbejdere;
    }

    public void createBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO bestillinger (bestilling_id, bestilling_dato, bestilling_time, kunde_id, klippetype_id, medarbejder_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bestilling.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bestilling.getBestilling_dato()));
            preparedStatement.setTime(3, Time.valueOf(bestilling.getBestilling_time()));
            preparedStatement.setInt(4, bestilling.getKunde().getId());
            preparedStatement.setInt(5, bestilling.getKlippetype().getId());
            preparedStatement.setInt(6, bestilling.getMedarbejder().getId());

            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Bestilling tilføjet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createKunde(Kunde kunde) {
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






