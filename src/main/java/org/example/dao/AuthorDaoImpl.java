package org.example.dao;

import org.example.database.ConnectionFactory;
import org.example.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl  implements AuthorDao{
    @Override
    public void save(Author author){
        String sql = "Insert into authors(name) values(?)";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, author.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Author> findAll() {

        List<Author> authors = new ArrayList<>();

        String sql = "SELECT * FROM authors";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Author author = new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );

                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public Author findById(int id) {

        String sql = "SELECT * FROM authors WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Нельзя удалить автора, пока существуют его книги.");
            return false;
        }
    }
}

