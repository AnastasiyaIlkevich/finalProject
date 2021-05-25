package com.finalProject.service;

import com.finalProject.dao.ApplicationDao;
import com.finalProject.dao.AuthorDao;
import com.finalProject.dao.BookDao;
import com.finalProject.model.Author;
import com.finalProject.model.Book;
import com.finalProject.model.Genre;

import java.sql.SQLException;
import java.util.Scanner;

import static com.finalProject.dao.AuthorDao.addNewAuthor;
import static com.finalProject.dao.GenreDao.SelectBookGenre;

public class ApplicationService {

    private static BookDao bookDao = new BookDao();
    private static AuthorDao authorDao = new AuthorDao();

    public static void restartProgram() {

        ApplicationDao.repeatProgram();

    }

    public static Book requestAddBook() throws SQLException {
        Book book = new Book();
        Author author = new Author();
        Genre genre = new Genre();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Введите НАЗВАНИЕ книги:");
        String bookTitle = scanner.nextLine();
        //проверка наличия такой книги.
        if (bookDao.CheckBookTitle(bookTitle) != null) {
            System.out.println("""
                    Книга с таким названием уже существует\s
                    Хотите все равно ее ввести?\s
                    1. Да.\s
                    2. Нет.\s""");
            int exPoint = scanner2.nextInt();
            if (exPoint == 2) {
                return null;
            }
        }
        //такой книги нет или ее хотят ввести все равно.
        book.setTitle(bookTitle);

        System.out.println("Введите IBSN книги:");
        book.setIBSN(scanner.nextLine());

        System.out.println("Имя автора книги:");
        String nameAuthor = scanner.nextLine();
        //проверка наличия такого автора.
        if (authorDao.CheckAuthorName(nameAuthor) != null) {// как это исправить? я дважды делаю одну операцию...
            // указываем существующее ид
            author.setId(authorDao.CheckAuthorName(nameAuthor));

        } else {
            author.setId(addNewAuthor(nameAuthor));
        }

        genre.setId(SelectBookGenre());
        book.setAuthor(author);
        book.setGenre(genre);
        bookDao.addNewBook(book);
        return book;
    }

    public void incorrectDataEntered() {

        System.out.println("Вы ввели неправильную команду.");
        System.out.println("Вас отправляет в главное меню.");
        ApplicationDao.repeatProgram();
    }
}

