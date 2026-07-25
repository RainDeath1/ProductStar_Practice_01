package org.example.cli;

import org.example.service.AuthorService;
import org.example.service.BookService;
import org.example.service.ReaderService;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Reader;


import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    private final AuthorService authorService = new AuthorService();
    private final BookService bookService = new BookService();
    private final ReaderService readerService = new ReaderService();

    public void start(){
        boolean running = true;

        while (running){
            System.out.println("\n================= БИБЛИОТЕКА =================");
            System.out.println("1. Добавить автора");
            System.out.println("2. Показать авторов");
            System.out.println();
            System.out.println("3. Добавить книгу");
            System.out.println("4. Показать книги");
            System.out.println();
            System.out.println("5. Добавить читателя");
            System.out.println("6. Показать читателей");
            System.out.println();
            System.out.println("7. Удалить автора");
            System.out.println("8. Удалить книгу");
            System.out.println("9. Удалить читателя");
            System.out.println();
            System.out.println("0. Выход\n=================");

            System.out.print("Выберите пункт: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
                continue;
            }

            switch (choice){
                case 1 -> addAuthor();
                case 2 -> showAuthors();
                case 3 -> addBook();
                case 4 -> showBooks();
                case 5 -> addReader();
                case 6 -> showReaders();
                case 7 -> deleteAuthor();
                case 8 -> deleteBook();
                case 9 -> deleteReader();
                case 0 ->{
                    running = false;
                    System.out.println("До свидания!");
                }

                default -> System.out.println("Неверный пункт меню");
            }
        }
        sc.close();
    }
    private void addAuthor(){
        System.out.print("Имя автора: ");
        String name = sc.nextLine();

        authorService.addAuthor(name);
        System.out.println("Автор добавлен");

    }

    private void showAuthors(){
        if (authorService.getAllAuthors().isEmpty()) {
            System.out.println("Авторов нет.");
            return;
        }

        authorService.getAllAuthors().forEach(System.out::println);
    }

    private void addBook(){
        showAuthors();

        System.out.print("Название: ");
        String title = sc.nextLine();

        System.out.print("Год издания: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("ID автора:");
        int authorId = Integer.parseInt(sc.nextLine());

        if (authorService.getAuthorById(authorId) == null) {
            System.out.println("Автор с таким ID не найден.");
            return;
        }

        bookService.addBook(title, year, authorId);

        System.out.println("Книга добавлена.");
    }

    private void showBooks(){
        if (bookService.getAllBooks().isEmpty()) {
            System.out.println("Книг нет.");
            return;
        }

        bookService.getAllBooks().forEach(System.out::println);
    }

    private void addReader(){
        System.out.print("Имя читателя: ");
        String name = sc.nextLine();

        System.out.print("Номер телефона: ");
        String phone = sc.nextLine();

        readerService.addReader(name, phone);

        System.out.println("Читатель добавлен.");
    }

    private void showReaders(){
        if (readerService.getAllReaders().isEmpty()) {
            System.out.println("Читателей нет.");
            return;
        }

        readerService.getAllReaders().forEach(System.out::println);
    }

    private void deleteAuthor(){
        showAuthors();

        System.out.print("ID автора: ");
        int id = Integer.parseInt(sc.nextLine());

        if (authorService.deleteAuthor(id)) {
            System.out.println("Автор удален.");
        } else {
            System.out.println("Удаление невозможно.");
        }
    }
    private void deleteBook(){
        showBooks();

        System.out.print("ID книги: ");
        int id = Integer.parseInt(sc.nextLine());

        Book book = bookService.getBookById(id);

        if (bookService.deleteBook(id)) {
            System.out.println("Книга удалена.");
        } else {
            System.out.println("Удаление невозможно.");
        }
    }

    private void deleteReader(){
        showReaders();

        System.out.print("ID читателя: ");
        int id = Integer.parseInt(sc.nextLine());

        Reader reader = readerService.getReaderById(id);

        if (reader == null) {
            System.out.println("Читатель не найден.");
            return;
        }

        readerService.deleteReader(id);

        System.out.println("Читатель удален.");
    }
}
