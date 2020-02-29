package src.library.service;

import src.library.database.Database;
import src.library.myObjects.Book;
import src.library.myObjects.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private static BookService bookService;
    private BookService(){
    }
    public static BookService getInstance(){
        if(bookService == null) return bookService = new BookService();
        return bookService;
    }

    public boolean createBook(Book book){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "INSERT INTO books (name, pages, description, state) VALUES (?, ?, ?, 'NOT_TAKEN')";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setInt(2, book.getPages());
                preparedStatement.setString(3, book.getDescription());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateBookById(int id, Book book){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "UPDATE books SET name = ?, pages = ?, description = ?, state = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setInt(2, book.getPages());
                preparedStatement.setString(3, book.getDescription());
                preparedStatement.setString(4, book.getState().toString());
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Book getBookById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "SELECT * FROM books WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    int pages = resultSet.getInt("pages");
                    String description = resultSet.getString("description");
                    State state = resultSet.getString("state").equals("TAKEN") ? State.TAKEN : State.NOT_TAKEN;
                    return new Book(name, description, pages, state);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Book> getAllBooks(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            List<Book> books = new ArrayList<>();
            if(connection != null){
                String query = "SELECT * FROM books";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    String name = resultSet.getString("name");
                    int pages = resultSet.getInt("pages");
                    String description = resultSet.getString("description");
                    State state = resultSet.getString("state").equals("TAKEN") ? State.TAKEN : State.NOT_TAKEN;
                    books.add(new Book(name, description, pages, state));
                }
                return books;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteBookById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "DELETE FROM books WHERE id = ?";
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
