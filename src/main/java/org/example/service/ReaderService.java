package org.example.service;


import org.example.dao.ReaderDao;
import org.example.dao.ReaderDaoImpl;
import org.example.entity.Reader;

import java.util.List;

public class ReaderService {

    private final ReaderDao readerDao = new ReaderDaoImpl();

    public void addReader(String name, String phone){
        readerDao.save(new Reader(name, phone));
    }

    public List<Reader> getAllReaders(){
        return readerDao.findAll();
    }

    public Reader getReaderById(int id){
        return readerDao.findById(id);
    }

    public void deleteReader(int id){
        readerDao.delete(id);
    }
}
