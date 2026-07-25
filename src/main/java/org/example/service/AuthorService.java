package org.example.service;

import org.example.dao.AuthorDao;
import org.example.dao.AuthorDaoImpl;
import org.example.entity.Author;

import java.util.List;

public class AuthorService {

    private final AuthorDao authorDao = new AuthorDaoImpl();

    public void addAuthor(String name){
        authorDao.save(new Author(name));
    }

    public List<Author> getAllAuthors(){
        return authorDao.findAll();
    }

    public Author getAuthorById(int id){
        return authorDao.findById(id);
    }

    public boolean deleteAuthor(int id){
        return authorDao.delete(id);
    }
}
