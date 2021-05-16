package com.finalProject;

import com.finalProject.dao.BookDao;
import com.finalProject.model.Book;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        BookDao bookDao = new BookDao();

        System.out.println(bookDao.getAllBooks());
        System.out.println(bookDao.getBookById(3L));



        Book book = new Book(3l);
        bookDao.delete(book);
        System.out.println(bookDao.getAllBooks());

    }

}
