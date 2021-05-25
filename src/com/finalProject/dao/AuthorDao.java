package com.finalProject.dao;

import com.finalProject.util.DataSourceUtil;

import java.sql.*;

public class AuthorDao {

   private static DataSourceUtil dataSourceUtil = new DataSourceUtil();


    //добавляет нового автора
    public static long addNewAuthor(String nameAuthor) throws SQLException {

        Long authorId = null;
        String sql = "insert authors(name) values(?)";
        Connection connection = dataSourceUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nameAuthor);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next())
        {
            authorId = Long.valueOf(rs.getInt(1));
            System.out.println("создало нового автора "+authorId);
        }
        dataSourceUtil.CloseConnection(connection);//закрываем соединение
        return authorId;
    }


    public Long CheckAuthorName(String nameAuthor) throws SQLException {

        String sql ="select authors.name, authors.id from authors " +
                "where authors.name = ?";
        Connection connection = dataSourceUtil.getConnection();//соединение с базой данных
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//посыльный в базу
        preparedStatement.setString(1, nameAuthor);
        preparedStatement.executeQuery();

        ResultSet rs = preparedStatement.executeQuery();;
        if(rs.next())
        {
            Long authorId = Long.valueOf(rs.getInt("authors.id"));
            System.out.println(authorId);
            return authorId;
        }
        dataSourceUtil.CloseConnection(connection);//закрываем соединение
        return null;



    }
}
