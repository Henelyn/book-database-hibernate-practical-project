package menu;

import model.Author;
import model.Book;
import model.Publisher;
import persistence.AuthorRepository;
import persistence.BookRepository;
import persistence.PublisherRepository;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookMenu {
    BookRepository bookRepository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();
    PublisherRepository publisherRepository = new PublisherRepository();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create new book");
        System.out.println("2: Update book");
        System.out.println("3: List of all books");
        System.out.println("4: Delete book");
        System.out.println("5: Find book by id");
        System.out.println("6: Update rating by book id");
        System.out.println("7: List all books registered last month");
        System.out.println("8: List all books registered yesterday");
        System.out.println("9: Find books by publisher");
        System.out.println("10: Find books by author");
        System.out.println("11: Get total number of books");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    createBook(input);
                    break;
                case 2:
                    break;
                case 3:
                    listAllBooks();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    updateRatingByBookId(input);
                    break;
                case 7:
                    listAllBooksRegisteredLastMonth();
                    break;
                case 8:
                    listAllBooksRegisteredYesterday();
                    break;
                case 9:
                    listAllBooksByPublisher(input);
                    break;
                case 10:
                    listAllBooksByAuthor(input);
                    break;
                case 11:
                    getBookTotalCount();
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }


    private void listAllBooks() {
        List<Book> listBook = bookRepository.listAllBooks();

        if (listBook.size() > 0) { //isEmpty, võid kontrollida
            System.out.println("\nList of books:");
            for (Book book : listBook) {
                System.out.println(book.toString());
            }
        } else {
            System.out.println("\nNo books registered\n");

            //   menuOptions(input);
        }
    }

    private void createBook(Scanner input) {
        System.out.println("Enter book title:");
        input.nextLine();
        String title = input.nextLine();
        System.out.println("Enter publish date (dd.mm.yyyy):");
        LocalDate publishDate = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Enter rating:");
        int rating = input.nextInt();
        System.out.println("Enter total pages:");
        int pages = input.nextInt();
        LocalDate dateOfRegister = LocalDate.now();
        System.out.println("Enter author id from list:");
        listAllAuthors();
        Author author = authorRepository.findAuthorById(input.nextInt());
        System.out.println("Enter publisher id from list:");
        listAllPublishers();
        Publisher publisher = publisherRepository.findPublisherById(input.nextInt());
        Book book = new Book(title, publishDate, rating, pages, dateOfRegister, author, publisher);
        bookRepository.saveBook(book);
    }

    private void updateRatingByBookId(Scanner input) {
        System.out.println("Enter book Id:");
        int bookId = input.nextInt();
        System.out.println("Enter new rating:");
        int newRating = input.nextInt();
        Book book = bookRepository.updateRatingByBookId(bookId, newRating);
        if (book != null) {
            System.out.println("Rating changed to " + book.getRating());
        } else {
            System.out.println("Given book does not exist, please try again");
            updateRatingByBookId(input);
        }
    }

    private void listAllBooksRegisteredLastMonth() {
        List<Book> books = bookRepository.findAllBooksByDateOfRegisterLastMonth();

        if (books.size() > 0) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books registered last month.");
        }
    }

    private void listAllBooksRegisteredYesterday() {
        List<Book> books = bookRepository.findAllBooksByDateOfRegisterYesterday();

        if (books.size() > 0) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books registered yesterday.");
        }
    }

    private void listAllAuthors() {
        List<Author> authors = authorRepository.listAllAuthors();
        if (authors.size() > 0) {
            for (Author author : authors) {
                System.out.println(author.getAuthorId() + " " + author.getFirstName() + " " +
                        author.getLastName() + " (" + author.getPseudonym() + ")");
            }
        } else {
            System.out.println("No authors found.");
        }
    }

    private void listAllPublishers() {
        List<Publisher> publishers = publisherRepository.listOfAllPublishers();
        if (publishers.size() > 0) {
            for (Publisher publisher : publishers) {
                System.out.println(publisher.getPublisherId() + " " + publisher.getName());
            }
        } else {
            System.out.println("No publisher found.");
        }
    }

    private void listAllBooksByPublisher(Scanner input) {
        System.out.println("Select publisher id:");
        listAllPublishers();
        int publisherId = input.nextInt();
        Publisher publisher = publisherRepository.findPublisherById(publisherId); //.findPublisherById(input.nextInt) = sama asi
        List<Book> books = bookRepository.findBooksByPublisher(publisher);
        //Title: Acb, Date published: 10-11-2021, Author: tbf , Publisher: ttt, Rating: 1 start
        if (books.size() != 0) {
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle() + ", Date published: " + book.getPublishDate() +
                        ", Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                        ", Publisher: " + book.getPublisher().getName() + ", Rating: " + book.getRating() + " star(s)");
            }
        } else {
            System.out.println("Couldn't find any books by this publisher :(.");
        }
    }
    private void listAllBooksByAuthor(Scanner input) {
        System.out.println("Select authors id:");
        listAllAuthors();
        int authorId = input.nextInt();
        Author author = authorRepository.findAuthorById(authorId); //.findPublisherById(input.nextInt) = sama asi
        List<Book> books = bookRepository.findBookByAuthor(author);
        //Title: Acb, Date published: 10-11-2021, Author: tbf , Publisher: ttt, Rating: 1 start
        if (books.size() != 0) {
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle() + ", Date published: " + book.getPublishDate() +
                        ", Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                        ", Publisher: " + book.getPublisher().getName() + ", Rating: " + book.getRating() + " star(s)");
            }
        } else {
            System.out.println("Couldn't find any books by this author :(.");
        }
    }

    private void getBookTotalCount() {
        System.out.println("Number of books in the database: " + bookRepository.findBookTotalCount());
    }
}
