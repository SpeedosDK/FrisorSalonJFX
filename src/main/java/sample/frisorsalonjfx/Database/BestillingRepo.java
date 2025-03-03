package sample.frisorsalonjfx.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Bestilling;
import sample.frisorsalonjfx.Model.Klippetype;
import sample.frisorsalonjfx.Model.Kunde;
import sample.frisorsalonjfx.Model.Medarbejder;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BestillingRepo  implements IBestillingRepository{

    @Override
    public void createBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO bestillinger (bestilling_dato, bestilling_tid, kunde_id, klippeType_id, medarbejder_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, bestilling.getId());
            preparedStatement.setTimestamp(1, Timestamp.valueOf(bestilling.getBestilling_dato()));
            preparedStatement.setTime(2, Time.valueOf(bestilling.getBestilling_time()));
            preparedStatement.setInt(3, bestilling.getKunde().getId());
            preparedStatement.setInt(4, bestilling.getKlippetype().getId());
            preparedStatement.setInt(5, bestilling.getMedarbejder().getId());

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
        String sql = "DELETE FROM bestillinger WHERE bestilling_id = ?";
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

    @Override
    public boolean isMedarbejderAvailable(int medarbejderId, LocalDateTime bestillingDato, LocalTime bestillingTid) {
        String sql = "SELECT COUNT(*) FROM bestillinger WHERE medarbejder_id = ? AND bestilling_dato = ? AND bestilling_tid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, medarbejderId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bestillingDato));
            preparedStatement.setTime(3, Time.valueOf(bestillingTid));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ObservableList<Bestilling> searchBestilling(String searchedName, String searchedMedarbejder, LocalDateTime searchedDate) {
        ObservableList<Bestilling> foundBestillinger = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bestillinger "
                + "JOIN kunder k ON bestillinger.kunde_id = k.kunde_id "
                + "JOIN medarbejder m ON bestillinger.medarbejder_id = m.medarbejder_id "
                + "WHERE (bestillinger.bestilling_dato = ?) "
                + "OR k.name LIKE ? "
                + "OR m.medarbejder_name LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setTimestamp(1, Timestamp.valueOf(searchedDate));
            preparedStatement.setString(2, "%" + searchedName + "%");
            preparedStatement.setString(3, "%" + searchedMedarbejder + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
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
                    foundBestillinger.add(new Bestilling(id, medarbejder, dato, tid, kunde, klippetype));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundBestillinger;
    }

}
