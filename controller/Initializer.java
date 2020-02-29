package src.library.controller;

import src.library.myObjects.Author;
import src.library.myObjects.Book;
import src.library.myObjects.Customer;
import src.library.service.*;

public class Initializer {
    public static void initialize(){
        SchemaConstructor schemaConstructor = SchemaConstructor.getInstance();
        schemaConstructor.constructTables();

        BookService bookService = BookService.getInstance();
        AuthorService authorService = AuthorService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        MainService mainService = MainService.getInstance();

        Book[] books = new Book[15];
        for(int i = 0; i < 15; i++) {
            books[i] = new Book("book"+i, "description"+i, i+10);
            bookService.createBook(books[i]);
        }

        Customer[] customers = new Customer[5];
        for(int i = 0; i < 5; i++){
            customers[i] = new Customer("customer"+i, "customeryan"+i,"123"+i);
            customerService.createCustomer(customers[i]);
        }

        Author[] authors = new Author[30];
        for(int i = 0; i < 30; i++){
            authors[i] = new Author("author"+i, "authoryanich"+i);
            authorService.createAuthor(authors[i]);
        }

        for(int i = 0, j = 0; i < 15; i++, j++){
            int[] authors1 = { j , j++};
            mainService.assignAuthorsToABookById(authors1, i+1);
        }
    }
}
