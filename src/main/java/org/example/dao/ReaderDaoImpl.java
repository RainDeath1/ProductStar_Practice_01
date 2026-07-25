package org.example.dao;

import org.example.database.ConnectionFactory;
import org.example.entity.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {

    @Override
    public void save(Reader reader) {

        String sql = "INSERT INTO readers(name, phone) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reader.getName());
            statement.setString(2, reader.getPhone());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reader> findAll() {

        List<Reader> readers = new ArrayList<>();

        String sql = "SELECT * FROM readers";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                readers.add(new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readers;
    }

    @Override
    public Reader findById(int id) {

        String sql = "SELECT * FROM readers WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM readers WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}