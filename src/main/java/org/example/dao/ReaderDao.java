package org.example.dao;

import java.util.List;
import org.example.entity.Reader;

public interface ReaderDao {

    void save(Reader reader);
    List<Reader> findAll();
    Reader findById(int id);
    void delete(int id);
}
