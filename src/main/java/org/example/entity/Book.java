package org.example.entity;

public class Book {
    private int id;
    private String title;
    private int publishYear;
    private int authorId;

    public Book(){}

    public Book(int id, String title, int publishYear, int authorId){
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.authorId = authorId;
    }

    public Book(String title, int publishYear, int authorId){
        this.title = title;
        this.publishYear = publishYear;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString(){
        return "Книга{" +
                "id" + id +
                ", Название = ' " + title + '\'' +
                ", Дата выпуска = " + publishYear +
                ", Автор = " + authorId + '}';
    }
}
