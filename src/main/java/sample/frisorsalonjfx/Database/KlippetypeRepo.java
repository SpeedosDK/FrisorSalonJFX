package sample.frisorsalonjfx.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.frisorsalonjfx.Klippetype;

import java.sql.*;

public class KlippetypeRepo implements IKlippeTypeRepository {
    @Override
    public void createKlippeType(Klippetype klippetype) {
        String sql = "INSERT INTO klippetype (klippetype_id, klipning, klippe_length, pris) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, klippetype.getId());
            preparedStatement.setString(2, klippetype.getKlippeStil());
            preparedStatement.setInt(3, klippetype.getTimeForCut());
            preparedStatement.setInt(4, klippetype.getPrice());
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Klippetype oprettet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ObservableList<Klippetype> readKlippeTyper() {
        ObservableList<Klippetype> klippetyper = FXCollections.observableArrayList();
        String sql = "SELECT * FROM klippetype";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("klippetype_id");
                String name = resultSet.getString("name");
                int tid= resultSet.getInt("Tid");
                int pris = resultSet.getInt("Pris");
                klippetyper.add(new Klippetype(id, name, tid, pris));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return klippetyper;
    }
    @Override
    public void deleteKlippeType(Klippetype klippetype) {
        String sql = "DELETE FROM klippetype WHERE klippetype_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, klippetype.getId());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Klippetype slettet");
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
