package sample.frisorsalonjfx.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.frisorsalonjfx.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BestillingRepo  implements IBestillingRepository{

    @Override
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
    @Override
    public ObservableList<Bestilling> readBestillinger() {
        ObservableList<Bestilling> bestillinger = FXCollections.observableArrayList();
        String sql = "SELECT b.bestilling_id, b.bestilling_dato, b.bestilling_tid, " +
                "k.kunde_id, k.name, k.tlfnr, k.email, " +
                "kl.klippeType_id, kl.klipning, kl.klippe_length, kl.pris, " +
                "m.medarbejder_id, m.name AS medarbejder_navn, m.admin " +
                "FROM bestillinger b " +
                "JOIN kunder k ON b.kunde_id = k.kunde_id " +
                "JOIN klippetype kl ON b.klippeType_id = kl.klippeType_id " +
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
                        resultSet.getString("medarbejder_navn"),
                        null, // password ligemeget
                        resultSet.getBoolean("admin")
                );

                // Opret Bestilling-objekt og tilføj til listen
                bestillinger.add(new Bestilling(id, medarbejder, dato, tid, kunde, klippetype));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bestillinger;
    }

    @Override
    public List<Bestilling> readBestillingerByMedarbejder(int medarbejderId) {
        List<Bestilling> bestillinger = new ArrayList<>();
        String sql = "SELECT * FROM bestillinger WHERE medarbejder_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, medarbejderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Create and add Bestilling objects to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bestillinger;
    }


    @Override
    public void deleteBestilling(Bestilling bestilling) {
        String sql = "DELETE FROM bestilling WHERE bestilling_id = ?";
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
