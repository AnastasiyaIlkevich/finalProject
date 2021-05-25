package com.finalProject.service;

import com.finalProject.dao.BookDao;
import com.finalProject.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BookService {

    private BookDao bookDao = new BookDao();


    public List<Book> fetchAllBook() throws SQLException {
        List<Book> booksList = new ArrayList<>();
        booksList.addAll(bookDao.getAllBooks());
        return booksList;
    }

    public Book fetchBookById(Long id) {
        try {
            return bookDao.getBookById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void deleteBook(Long id) {
        try {
            bookDao.deleteBook(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Book correctionTitle(Long bookId, String newTitleBook) {

        try {
            return     bookDao.correctionBookTitle(bookId, newTitleBook );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         return null;// не придумала как обработать если команда не правильная...
    }

    public Book correctionAuthor() {
        return null;
    }

    public Book correctionGenre(Long  bookId ) {


        try {
            return     bookDao.correctionBookGenre(  bookId );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;// не придумала как обработать если команда не правильная...
    }
}



