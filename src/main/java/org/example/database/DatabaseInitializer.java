package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseInitializer {
    public static void initialize(){
        try (Connection connection
                = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement()) {
            statement.execute("""
                    Create table if not exists authors(
                        id int Auto_increment primary key,
                        name varchar(100) not null
                    )
                    """);

            statement.execute("""
                    Create table if not exists books(
                        id int Auto_increment primary key,
                        title varchar(200) not null,
                        publish_year int not null,
                        author_id int not null,
                        foreign key (author_id) references authors(id)
                    )
                    """);

            statement.execute("""
                    Create table if not exists readers(
                        id int Auto_increment primary key,
                        name varchar(100) not null,
                        phone varchar(30) not null
                    )
                    """);
            System.out.println("База данных успешно инициализорвана.");

        } catch (SQLException e) {
            System.out.println("Ошибка при создании базы данных");
            e.printStackTrace();
        }
        }
    }

