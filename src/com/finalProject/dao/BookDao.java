package com.finalProject.dao;

import com.finalProject.model.Author;
import com.finalProject.model.Book;
import com.finalProject.model.Genre;
import com.finalProject.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalProject.dao.GenreDao.SelectBookGenre;

public class BookDao {


    private DataSourceUtil dataSourceUtil = new DataSourceUtil();

    //вывод всех книг.
    public List<Book> getAllBooks() throws SQLException {

        String sql = "select books.id, books.title," +
                " books.ISBN, authors.name,  " +
                "genres.name, authors.id, genres.id from books, genres, authors " +
                "where books.genreID = genres.id " +
                "and books.authorId = authors.id ";

        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        Statement statement = connection.createStatement();//посыльный в базу
        ResultSet resultSet = statement.executeQuery(sql);//запрос в базу данных

        return createBook(resultSet, connection);

    }

    // метод достать книгу  по id
    public Book getBookById(Long id) throws SQLException {

        String sql = "select books.id, books.title," +
                " books.ISBN, authors.name,  " +
                "genres.name, authors.id, genres.id from books, genres, authors " +
                "where books.genreId = genres.id " +
                "and books.authorId = authors.id " +
                "and books.id = ?";

        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//посыльный в базу
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        //не работает

//        int resultCount = resultSet.getRow(); //количество записей
//        if(resultCount==0) {
//            dataSourceUtil.CloseConnection(connection);//закрываем соединение
//            System.out.println("Не правильно введен ID");
//            return null;
//        } else {
            return createBook(resultSet, connection).get(0);
//        }
    }

    //удаление книги по ид
    public void deleteBook(Long id) throws SQLException {//нужна проверка есть ли книга с таким ID
        Connection connection = dataSourceUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        dataSourceUtil.CloseConnection(connection);//закрываем соединение
        System.out.println("Книга удалена.");
    }


    //Добавить книгу, вывод в консоль добавленной книги.
    // если делать через отдельные методы в результате книга вводится но в
    // консольвыводится null. Как это решить не нашла, потому написала в последовательность работы в одном методе.
    public List<Book> addNewBook(Book book) throws SQLException {

        String sql = "insert books(title,ISBN, authorId,genreId) values(?,?,?,?)";

        Connection connection = dataSourceUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getIBSN());
        preparedStatement.setLong(3, book.getAuthor().getId());
        preparedStatement.setLong(4, book.getGenre().getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        Long bookId = null;
        if (rs.next())// не знаю как вызвать последнюю добавленную книгу. Поэтому делаю так.
        {
            bookId = rs.getLong(1);
            System.out.println(bookId);
        }
        String sql2 = "select books.id, books.title," +
                " books.ISBN, authors.name,  " +
                "genres.name, authors.id, genres.id from books, genres, authors " +
                "where books.genreId = genres.id " +
                "and books.authorId = authors.id " +
                "and books.id = ?";

        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);//посыльный в базу
        preparedStatement2.setLong(1, bookId);
        ResultSet resultSet2 = preparedStatement2.executeQuery();

        List<Book> books = new ArrayList<>();
        while (resultSet2.next()) {
            Author author = new Author();
            Genre genre = new Genre();
            book.setId(resultSet2.getLong(1));
            book.setTitle(resultSet2.getString(2));
            book.setIBSN(resultSet2.getString(3));
            author.setName(resultSet2.getString(4));
            author.setId(resultSet2.getLong(6));
            book.setAuthor(author);
            genre.setName(resultSet2.getString(5));
            genre.setId(resultSet2.getLong(7));
            book.setGenre(genre);
            books.add(book);
        }
        dataSourceUtil.CloseConnection(connection);//закрываем соединение

        return books;
    }


    //Изменить название автора или жанр


    private List<Book> createBook(ResultSet resultSet, Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            Author author = new Author();
            Genre genre = new Genre();
            book.setId(resultSet.getLong(1));
            book.setTitle(resultSet.getString(2));
            book.setIBSN(resultSet.getString(3));
            author.setName(resultSet.getString(4));
            author.setId(resultSet.getLong(6));
            book.setAuthor(author);
            genre.setName(resultSet.getString(5));
            genre.setId(resultSet.getLong(7));
            book.setGenre(genre);
            books.add(book);
        }
        dataSourceUtil.CloseConnection(connection);//закрываем соединение

        return books;
    }


    public Book CheckBookTitle(String bookTitle) throws SQLException {


        String sql = "select books.id, books.title," +
                " books.ISBN, authors.name,  " +
                "genres.name, authors.id, genres.id from books, genres, authors " +
                "where books.genreId = genres.id " +
                "and books.authorId = authors.id " +
                "and books.title = ?";

        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//посыльный в базу
        preparedStatement.setString(1, bookTitle);
        ResultSet resultSet = preparedStatement.executeQuery();

        //не работает

//        int resultCount = resultSet.getRow(); //количество записей
//        if(resultCount==0) {
//            dataSourceUtil.CloseConnection(connection);//закрываем соединение
//            return null;
//        } else {
            return createBook(resultSet, connection).get(0);
//        }
    }

    public Book correctionBookTitle(Long bookId, String newTitleBook) throws SQLException {
        String sql = "UPDATE books SET title=? WHERE id = ?";
        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//посыльный в базу
        preparedStatement.setString(1, newTitleBook);
        preparedStatement.setLong(2, bookId);
        preparedStatement.executeUpdate();
        dataSourceUtil.CloseConnection(connection);//закрываем соединение
return getBookById(bookId);
    }

    public Book correctionBookGenre(Long  bookId ) throws SQLException {

        Genre genre = new Genre();
        String sql = "UPDATE books SET books.genreId=? WHERE  books.Id = ?";
        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//посыльный в базу
        preparedStatement.setLong(1, SelectBookGenre());
        preparedStatement.setLong(2, bookId);
        preparedStatement.executeUpdate();
        dataSourceUtil.CloseConnection(connection);//закрываем соединение
        return getBookById(bookId);
    }
}
