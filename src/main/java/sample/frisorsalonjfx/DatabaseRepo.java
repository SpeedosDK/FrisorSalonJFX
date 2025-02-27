package sample.frisorsalonjfx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;
import java.time.*;



public class DatabaseRepo {

    public void createMedarbejder(Medarbejder medarbejder) {
        String sql = "INSERT INTO medarbejder (name, password) VALUES (?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medarbejder.getName());
            preparedStatement.setString(2, medarbejder.getPassword());

            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Medarbejder tilføjet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public ObservableList<Medarbejder> readMedarbejdere() {
        ObservableList<Medarbejder> medarbejdere = FXCollections.observableArrayList();
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
    public ObservableList<Kunde> readKunder() {
        ObservableList<Kunde> kunder = FXCollections.observableArrayList();
        String sql = "SELECT * FROM kunder";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("kunde_id");
                String name = resultSet.getString("name");
                int tlfnr = resultSet.getInt("tlfnr");
                String email = resultSet.getString("email");
                kunder.add(new Kunde(id, name, tlfnr, email));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return kunder;


    }

    public void createBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO bestillinger (bestilling_id, bestilling_dato, bestilling_tid, kunde_id, klippeType_id, medarbejder_id) VALUES (?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO kunder (kunde_id, name, tlfnr, email) VALUES (?, ?, ?, ?)";

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

    public List<Bestilling> readBestillinger() {
        List<Bestilling> bestillinger = new ArrayList<>();
        String sql = "SELECT b.bestilling_id, b.bestilling_dato, b.bestilling_tid, " +
                "k.kunde_id, k.name, k.tlfnr, k.email, " +
                "kl.klippeType_id, kl.klipning, kl.klippe_length, kl.pris, " +
                "m.medarbejder_id, m.name AS medarbejder_navn " +
                "FROM bestillinger b " +
                "JOIN kunder k ON b.kunde_id = k.kunde_id " +
                "JOIN klippetyper kl ON b.klippeType_id = kl.klippeType_id " +
                "JOIN medarbejder m ON b.medarbejder_id = m.medarbejder_id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("bestilling_id");
                LocalDateTime dato = resultSet.getTimestamp("bestilling_dato").toLocalDateTime();
                LocalTime tid = resultSet.getTime("bestilling_tid").toLocalTime();

                // Opret kunde-objekt
                Kunde kunde = new Kunde(
                        resultSet.getInt("kunde_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("tlfnr"),
                        resultSet.getString("email")
                );

                // Opret klippetype-objekt
                Klippetype klippetype = new Klippetype(
                        resultSet.getInt("klippeType_id"),
                        resultSet.getString("klipning"),
                        resultSet.getInt("klippe_length"),
                        resultSet.getInt("pris")
                );

                // Opret medarbejder-objekt
                Medarbejder medarbejder = new Medarbejder(
                        resultSet.getInt("medarbejder_id"),
                        resultSet.getString("name"),
                        null // Password er ikke nødvendigt her
                );

                // Opret Bestilling-objekt og tilføj til listen
                bestillinger.add(new Bestilling(id, medarbejder, dato, tid, kunde, klippetype));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bestillinger;
    }

    public void deleteBestilling(Bestilling bestilling) {
        String sql = "DELETE FROM bestilling WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bestilling.getId());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Bestilling slettet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





