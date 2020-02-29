package src.library.service;

import src.library.database.Database;
import src.library.myObjects.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    private static AuthorService authorService;
    private AuthorService(){}
    public static AuthorService getInstance(){
        if(authorService == null) return authorService = new AuthorService();
        else return authorService;
    }

    public boolean createAuthor(Author author){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "INSERT INTO authors (name, surname) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAuthorById(int id, Author author){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Author getAuthorById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "SELECT * FROM authors WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    return new Author(name, surname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Author> getAllAuthors(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                List<Author> authors = new ArrayList<>();
                String query = "SELECT * FROM authors";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    authors.add(new Author(name, surname)) ;
                }
                return authors;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteAuthorById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "DELETE FROM authors WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
