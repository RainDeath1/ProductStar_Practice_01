package org.example.service;

import org.example.dao.BookDao;
import org.example.dao.BookDaoImpl;
import org.example.entity.Book;

import java.util.List;

public class BookService {

    private final BookDao bookDao = new BookDaoImpl();

    public void addBook(String title, int publishYear, int authorId){
        bookDao.save(new Book(title, publishYear, authorId));
    }

    public List<Book> getAllBooks(){
        return bookDao.findAll();
    }

    public Book getBookById(int id){
        return bookDao.findById(id);
    }

    public boolean deleteBook(int id){
        return bookDao.delete(id);
    }
}
