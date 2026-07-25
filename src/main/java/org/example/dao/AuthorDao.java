package org.example.dao;

import org.example.entity.Author;
import java.util.List;

public interface AuthorDao {
    void save(Author author);
    List<Author> findAll();
    Author findById(int id);
    boolean delete(int id);
}
