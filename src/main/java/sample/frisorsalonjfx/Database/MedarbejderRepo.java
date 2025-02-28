package sample.frisorsalonjfx.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Medarbejder;

import java.sql.*;

public class MedarbejderRepo implements IMedarbejderRepository {

    @Override
    public void createMedarbejder(Medarbejder medarbejder) {
        String sql = "INSERT INTO medarbejder (name, password, admin) VALUES (?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medarbejder.getName());
            preparedStatement.setString(2, medarbejder.getPassword());
            preparedStatement.setBoolean(3, medarbejder.isAdmin());

            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Medarbejder tilføjet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteMedarbejder(Medarbejder medarbejder) {
        String sql = "DELETE FROM medarbejder WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, medarbejder.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Fjernet medarbejder");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
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
                Boolean isAdmin = resultSet.getBoolean("admin");
                medarbejdere.add(new Medarbejder(id, name, password, isAdmin));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return medarbejdere;
    }
}
