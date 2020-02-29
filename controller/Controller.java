package src.library.controller;

import src.library.myObjects.Author;
import src.library.myObjects.Book;
import src.library.service.AuthorService;
import src.library.service.BookService;
import src.library.service.CustomerService;
import src.library.service.MainService;

import java.util.Scanner;

public class Controller {
    private static Controller controller;
    private BookService bookService = BookService.getInstance();
    private AuthorService authorService = AuthorService.getInstance();
    private CustomerService customerService = CustomerService.getInstance();
    private MainService mainService = MainService.getInstance();
    private Controller(){}
    public static Controller getInstance(){
        if(controller == null) return controller = new Controller();
        return controller;
    }

    public void menu(){
        Initializer.initialize();

        Scanner scanner = new Scanner(System.in);
        boolean inProcess = true;
        do {
            System.out.println("Welcom to the library");
            System.out.println("enter 1 to access books");
            System.out.println("enter 2 to access authors");
            System.out.println("enter 3 to access customers(admin only)");
            System.out.println("enter 5 to become a customer");
            System.out.println("enter 6 to order books");
            System.out.println("enter 7 to exit");
            int x;
            try {
                x = scanner.nextInt();
            }catch (Exception e){
                System.out.println("Input is illegal");
                continue;
            }
            scanner.nextLine();

            switch (x){
                case 1: this.bookSection(scanner); break;
                case 2: authorService.getAllAuthors(); break;
            }
        }while (inProcess);
    }

    private void bookSection(Scanner scanner){
        boolean inProcess = true;
        do {
            System.out.println("welcome to book section");
            System.out.println("enter 1 to create a book");
            System.out.println("enter 2 to get a book");
            System.out.println("enter 3 to get all books");
            System.out.println("enter 4 to delete a book");
            System.out.println("enter 5 to go back");

            int x;
            try {
                x = scanner.nextInt();
            }catch (Exception e){
                System.out.println("oops something illegal was entered");
                continue;
            }
            scanner.nextLine();
            switch (x){
                case 1:
                    System.out.println("enter bookName");
                    String name = scanner.nextLine();
                    System.out.println("enter book description");
                    String description = scanner.nextLine();
                    System.out.println("enter number of pages");
                    int pages = Integer.parseInt(scanner.nextLine());
                    Book book = new Book(name, description, pages);
                    bookService.createBook(book);
                    scanner.nextLine();

                    System.out.println("enter author of the book(lazy to do for authors)");
                    System.out.println("enter author name");
                    String aName = scanner.nextLine();
                    System.out.println("enter author surname");
                    String surname = scanner.nextLine();
                    Author author = new Author(aName, surname);
                    authorService.createAuthor(author);

                    System.out.println("check the ids now then hit enter");
                    scanner.nextLine();
                    System.out.println("input aaaaahhhh the ids");
                    int a = scanner.nextInt();
                    scanner.nextLine();
                    int b = scanner.nextInt();
                    scanner.nextLine();
                    int[] ass = {a};
                    mainService.assignAuthorsToABookById(ass, b);
                    System.out.println("done here probbly");
                    break;
                case 2:
                    System.out.println("books id?");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("here is your book " + bookService.getBookById(id));
                    break;
                case 3:
                    System.out.println(bookService.getAllBooks());
                    break;
                case 4:
                    System.out.println("books id?");
                    int id1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(bookService.deleteBookById(id1));
                    break;
                case 5:
                    inProcess = false;
                    System.out.println("cya");
            }
        }while (inProcess);
    }
}
