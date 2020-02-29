package src.library.service;

import src.library.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SchemaConstructor {
    private static SchemaConstructor schemaConstructor;
    private boolean constructed = false;
    private SchemaConstructor() {
    }
    public static SchemaConstructor getInstance(){
        if(schemaConstructor == null) return schemaConstructor = new SchemaConstructor();
        return schemaConstructor;
    }

    private boolean createBooksTable(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "CREATE table IF NOT EXISTS books(\n" +
                        "    id int NOT NULL AUTO_INCREMENT,\n" +
                        "    name VARCHAR(255) NOT NULL,\n" +
                        "    pages int NOT NULL ,\n" +
                        "    description VARCHAR(500) NOT NULL ,\n" +
                        "    state VARCHAR(255) NOT NULL,\n" +
                        "    PRIMARY KEY (id)\n" +
                        ");";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean createAuthorsTable(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "CREATE table IF NOT EXISTS authors(\n" +
                        "    id int NOT NULL AUTO_INCREMENT,\n" +
                        "    name VARCHAR(255) NOT NULL,\n" +
                        "    surname VARCHAR(255) NOT NULL,\n" +
                        "    PRIMARY KEY (id)\n" +
                        ");";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean createAuthorsToBooksJoinTable(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "CREATE TABLE IF NOT EXISTS AuthorsToBooksJoin(\n" +
                        "    id int NOT NULL AUTO_INCREMENT,\n" +
                        "    author_id int NOT NULL,\n" +
                        "    book_id int NOT NULL,\n" +
                        "    PRIMARY KEY (id),\n" +
                        "    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                        "    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                        "    UNIQUE KEY (author_id, book_id)\n" +
                        ");";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean createClientsTable(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "CREATE TABLE IF NOT EXISTS clients(\n" +
                        "    id int NOT NULL AUTO_INCREMENT,\n" +
                        "    name VARCHAR(255) NOT NULL,\n" +
                        "    surname VARCHAR(255) NOT NULL,\n" +
                        "    password VARCHAR(800) NOT NULL,\n" +
                        "    PRIMARY KEY (id)\n" +
                        ");";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean createOrdersTable(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "CREATE TABLE IF NOT EXISTS orders(\n" +
                        "    id int NOT NULL AUTO_INCREMENT,\n" +
                        "    return_date VARCHAR(255) NOT NULL,\n" +
                        "    client_id int NOT NULL,\n" +
                        "    book_id int NOT NULL,\n" +
                        "    PRIMARY KEY (id),\n" +
                        "    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                        "    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ");";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean constructTables(){
        if(!constructed){
            this.createBooksTable();
            this.createAuthorsTable();
            this.createAuthorsToBooksJoinTable();
            this.createClientsTable();
            this.createOrdersTable();
        }
        constructed = true;
        return constructed;
    }

    //for development purpose only
    public void dropKid(){
        try(Connection connection = DriverManager.getConnection(Database.URL, Database.USER, Database.PASS)){
            if(connection != null){
                String query = "drop table AuthorsToBooksJoin;\n" +
                        "drop table orders;\n" +
                        "drop table authors;\n" +
                        "drop table books;\n" +
                        "drop table clients;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
