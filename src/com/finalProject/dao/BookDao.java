package com.finalProject.dao;

import com.finalProject.model.Author;
import com.finalProject.model.Book;
import com.finalProject.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {


    private DBUtils dbUtils = new DBUtils();

    //вывод всех книг.
    public List<Book> getAllBooks() throws SQLException {
        Connection connection = dbUtils.getConnection();//соединение с базой данных
        Statement statement = connection.createStatement();//посыльный в базу
        ResultSet resultSet = statement.executeQuery("select books.id, books.title," +
                " books.ISBN, authors.name, " +
                "genres.name from books, genres, authors " +
                "where books.genreID = genres.id " +
                "and books.authorId = authors.id ");//запрос в базу данных

        return createBook(resultSet, connection);

    }

    //10 метод достать книгу  по id
    public Book getBookById(Long id) throws SQLException {

        Connection connection = dbUtils.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select books.id, books.title," +
                        " books.ISBN, authors.name, " +
                        "genres.name from books, genres, authors " +
                        "where books.genreID = genres.id " +
                        "and books.authorId = authors.id " +
                        "and books.id = ?");//посыльный в базу
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return createBook(resultSet, connection).get(0);
    }

    private List<Book> createBook(ResultSet resultSet, Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();

            book.setId(resultSet.getLong(1));
            book.setTitle(resultSet.getString(2));
            book.setIBSN(resultSet.getString(3));
            book.setAuthor(resultSet.getString(4));// изменила Author на String...
            book.setGenre(resultSet.getString(5));// изменила Genre на String...
            books.add(book);
        }
        dbUtils.CloseConnection(connection);//закрываем соединение

        return books;
    }


//    //Добавить книгу.
//    public Book addBook( String title, String ISBN, String author, String genre) throws SQLException {
//        Book book = new Book();
//        Connection connection = dbUtils.getConnection();//соединение с базой данных
//        PreparedStatement preparedStatement2 = connection.prepareStatement("insert authors(name) values(?)");
//        preparedStatement2.setString(2, book.getAuthor());
//        PreparedStatement preparedStatement = connection.prepareStatement("insert books(title,ISBN," +
//                "authorId,genreId) values(?,?,?,?)");
//
//       // preparedStatement.setLong(1, book.getId());
//        preparedStatement.setString(2, book.getTitle());
//        preparedStatement.setString(3, book.getIBSN());
//        preparedStatement.setLong(4, book.getAuthor().getId());
//       // preparedStatement.setLong(3, book.getAuthor().getId());
//        preparedStatement.setLong(4, book.getGenre().getId());
//        preparedStatement.executeUpdate();
//        preparedStatement.setLong(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        return createBook(resultSet, connection).get(0);
//    }


    //проверить есть ли такое название.
    //есть-вернуть информацию о книге
    //нет-добавить книгу-вернуть информацию о книге
    //найти жанр в списке и добавить ид в таблицу книги-вернуть информацию о книге
    // проверить автор
    //есть-добавить ид в таблицу книги-вернуть информацию о книге
    //нет-добавить автора и ид в таблицу книги-вернуть информацию о книге


    //Изменить название автора или жанр


    //удаление книги по ид

    public void delete(Book book) throws SQLException {
        Connection connection = dbUtils.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(
               "DELETE FROM books WHERE id = ?");
//        PreparedStatement preparedStatement = connection.prepareStatement(
////                "DELETE FROM books.id, books.title," +
////                        " books.ISBN, authors.name, " +
////                        "genres.name from books, genres, authors " +
////                        "where books.genreID = genres.id " +
////                        "and books.authorId = authors.id " +
////                        "and books.id = ?");//посыльный в базу
        preparedStatement.setLong(1,book.getId());
        dbUtils.CloseConnection(connection);//закрываем соединение
        System.out.println("книга удалена успешно");


    }

//    public List<Book> delete(int id) throws SQLException {
//        Connection connection = dbUtils.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
//       /* PreparedStatement preparedStatement = connection.prepareStatement(
//                "DELETE FROM books.id, books.title," +
//                        " books.ISBN, authors.name, " +
//                        "genres.name from books, genres, authors " +
//                        "where books.genreID = genres.id " +
//                        "and books.authorId = authors.id " +
//                        "and books.id = ?");//посыльный в базу*/
//        preparedStatement.setLong(1, id);
//        //dbUtils.CloseConnection(connection);//закрываем соединение
//        ResultSet resultSet = preparedStatement.executeQuery("select books.id, books.title," +
//                " books.ISBN, authors.name, " +
//                "genres.name from books, genres, authors " +
//                "where books.genreID = genres.id " +
//                "and books.authorId = authors.id ");
//        return createBook(resultSet, connection);
//    }

}
