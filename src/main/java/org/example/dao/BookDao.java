package org.example.dao;

import java.util.List;
import org.example.entity.Book;

public interface BookDao {

        void save(Book book);
        List<Book> findAll();
        Book findById(int id);
        boolean delete(int id);


}
