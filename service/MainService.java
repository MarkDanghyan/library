package src.library.service;

import src.library.database.Database;
import src.library.myObjects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    private static MainService mainService;
    private MainService(){}
    public static MainService getInstance(){
        if(mainService == null) return mainService = new MainService();
        return mainService;
    }
    public boolean assignAuthorsToABookById(int[] authorsIds, int bookId){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                int n = authorsIds.length;
                for(int i = 0; i < n; i++){
                    String query = "INSERT INTO authorstobooksjoin(author_id, book_id) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, authorsIds[i]);
                    preparedStatement.setInt(2, bookId);
                    preparedStatement.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean assignBooksToAnAuthor(int[] booksIds, int authorId){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                int n = booksIds.length;
                for(int i = 0; i < n; i++){
                    String query = "INSERT INTO authorstobooksjoin(author_id, book_id) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, authorId);
                    preparedStatement.setInt(2, booksIds[i]);
                    preparedStatement.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> getAllBooksOfAnAuthorById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                List<Book> books = new ArrayList<>();
                String query = "SELECT book_id FROM authorstobooksjoin WHERE author_id = ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                BookService bookService = BookService.getInstance();
                while (resultSet.next()) {
                    books.add(bookService.getBookById(resultSet.getInt("id")));
                }
                return books;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Author> getAllAuthorsOfABookById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                List<Author> authors = new ArrayList<>();
                String query = "SELECT author_id FROM authorstobooksjoin WHERE book_id = ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                AuthorService authorService = AuthorService.getInstance();
                while (resultSet.next()) {
                    authors.add(authorService.getAuthorById(resultSet.getInt("id")));
                }
                return authors;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int loginCustomer(String name, String surname, String password){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                String query = "SELECT * FROM clients WHERE name = ? && surname = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    if(Customer.checkPassword(password, resultSet.getString("password")))
                        return resultSet.getInt("id");
                }
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean createOrder(int yourId, int bookId, Order order){
        BookService bookService = BookService.getInstance();
        Book book = bookService.getBookById(bookId);
        bookService.updateBookById(bookId, new Book(book.getName(), book.getDescription(), book.getPages(), State.TAKEN));
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)) {
            if(connection != null){
                String query = "INSERT INTO orders(return_date, client_id, book_id) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, order.getReturnDate());
                preparedStatement.setInt(2, yourId);
                preparedStatement.setInt(3, bookId);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
