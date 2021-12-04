package menu;

import model.Author;
import model.Country;
import model.Publisher;
import persistence.CountryRepository;
import persistence.PublisherRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class PublisherMenu {
    PublisherRepository publisherRepository = new PublisherRepository();
    CountryRepository countryRepository = new CountryRepository();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create new publisher");
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
                    createPublisher(input);
                    break;
                case 2:
                    break;
                case 3:
                    listAllPublishers();
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


    private void listAllPublishers() {
        List<Publisher> listPublishers = publisherRepository.listOfAllPublishers();

        if (listPublishers.size() > 0) { //isEmpty, võid kontrollida
            System.out.println("\nList of publishers:");
            for (Publisher publisher : listPublishers) {
                System.out.println(publisher.toString());
            }
        } else {
            System.out.println("\nNo such publisher registered\n");

            //   menuOptions(input);
        }
    }

    private void createPublisher(Scanner input) {
        input.nextLine();
        System.out.println("Enter publisher:");
        String publisherName = input.nextLine();
        System.out.println("Enter country id from list:");
        listAllCountries();
        Country country = countryRepository.findCountryById(input.nextInt());
        System.out.println("Enter yearn of establishment:");
        LocalDate yearOfEstablishment = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Publisher publisher = new Publisher(publisherName,yearOfEstablishment,country );
        publisherRepository.savePublisher(publisher);
    }

    private void listAllCountries() {
        List<Country> countries = countryRepository.listAllCountries();
        if (countries.size() > 0) {
            for (Country country : countries) {
                System.out.println(country.getCountryId() + " " + country.getCountry());
            }
        } else {
            System.out.println("No countries found.");
        }
    }
}
