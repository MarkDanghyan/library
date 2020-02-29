package src.library.service;

import src.library.database.Database;
import src.library.myObjects.Customer;

import java.sql.*;

public class CustomerService {
    private static CustomerService customerService;
    private CustomerService(){}
    public static CustomerService getInstance(){
        if(customerService == null) return customerService = new CustomerService();
        return customerService;
    }

    public boolean createCustomer(Customer customer){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "INSERT INTO clients (name, surname, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getEncodedPassword());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateCustomerById(int id, Customer customer){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "UPDATE clients SET name = ?, surname = ?, password = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getEncodedPassword());
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteCustomerById(int id){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "DELETE FROM clients WHERE id = ?";
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
