package sample.frisorsalonjfx.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Model.Kunde;

import java.sql.*;

public class KundeRepo implements IKundeRepository {
    @Override
    public void createKunde(Kunde kunde) {
        String sql = "INSERT INTO kunder ( name, tlfnr, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, kunde.getName());
            preparedStatement.setInt(2, kunde.getTelefon());
            preparedStatement.setString(3, kunde.getEmail());
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Kunde oprettet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
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
    @Override
    public void deleteKunde(Kunde kunde) {
        String sql = "DELETE FROM kunder WHERE kunde_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, kunde.getId());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("kunde slettet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
