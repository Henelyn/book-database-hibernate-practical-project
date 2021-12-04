package menu;

import model.Author;
import model.Book;
import persistence.AuthorRepository;
import persistence.BookRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AuthorMenu {
    AuthorRepository authorRepository = new AuthorRepository();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create new author");
        System.out.println("2: Update author");
        System.out.println("3: List all authors");
        System.out.println("4: Delete author");
        System.out.println("5: Find author by id");
        System.out.println("6: Find authors by date of birth");
        System.out.println("7: Find authors by date of birth range");
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
                    createAuthor(input);
                    break;
                case 2:
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    authorsByDateOfBirth(input);
                    break;
                case 7:
                    authorsByDateOfBirthRange(input);
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


    private void listAllAuthors() {
        List<Author> listAuthors = authorRepository.listAllAuthors();

        if (listAuthors.size() > 0) { //isEmpty, võid kontrollida
            System.out.println("\nList of authors:");
            for (Author author : listAuthors) {
                System.out.println(author.toString());
            }
        } else {
            System.out.println("\nNo authors registered\n");

            //   menuOptions(input);
        }
    }

    private void createAuthor(Scanner input) {
        System.out.println("Enter author first name:");
        input.nextLine();
        String firstName = nameValidation(input);
        System.out.println("Enter authors last name:");
        String lastName = nameValidation(input);
        System.out.println("Enter authors pseudonym:");
        String pseudonym = input.nextLine();
        System.out.println("Enter authors date of birth (dd.mm.yyyy):");
        LocalDate dateOfBirth = dateValidation(input);

        Author author = new Author(firstName, lastName, pseudonym, dateOfBirth);
        authorRepository.saveAuthor(author);
    }

    private String nameValidation(Scanner input) {
        while (true) {
            String name = input.nextLine();
            if (name.matches("^[a-zA-Z]{3,20}$")) {
                return name;
            }
            System.out.println("Name must contain only letters, can not contain special character and numbers," +
                    " minimum size 3, maximum 20");
        }
    }

    private LocalDate dateValidation(Scanner input) {
        while (true) {
            try {
                return LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (Exception e) {
                System.out.println("Please insert a valid date of birth in the following format (dd.MM.yyyy)");
            }
        }
    }

    private void authorsByDateOfBirth(Scanner input) {
        System.out.println("Enter authors date of birth (dd.mm.yyyy):");
        LocalDate dateOfBirth = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        List<Author> authors = authorRepository.findAuthorsByDateOfBirth(dateOfBirth);
        if (authors.size() > 0) {
            for (Author author : authors) {
                System.out.println(author);
            }
        } else {
            System.out.println("No authors found with given date.");
        }
    }

    private void authorsByDateOfBirthRange(Scanner input) {
        System.out.println("Enter authors date of birth (dd.mm.yyyy) from:");
        LocalDate dobFrom = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Enter authors date of birth (dd.mm.yyyy) to:");
        LocalDate dobTo = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        List<Author> authors = authorRepository.findAuthorsByDateOfBirthRange(dobFrom, dobTo);
        if (authors.size() > 0) {
            for (Author author : authors) {
                System.out.println(author);
            }
        } else {
            System.out.println("No authors found with given date range.");
        }
    }
}
