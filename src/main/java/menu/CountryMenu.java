package menu;

import model.Author;
import model.Country;
import persistence.AuthorRepository;
import persistence.CountryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CountryMenu {
    CountryRepository countryRepository = new CountryRepository();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create new country");
        System.out.println("2: List of all countries");
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
                    createCountry(input);
                    break;
                case 2:
                    listAllCountries();
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


    private void listAllCountries() {
        List<Country> listCountries = countryRepository.listAllCountries();

        if (listCountries.size() > 0) { //isEmpty, võid kontrollida
            System.out.println("\nList of countries:");
            for (Country country : listCountries) {
                System.out.println(country.toString());
            }
        } else {
            System.out.println("\nNo such country registered\n");

            //   menuOptions(input);
        }
    }

    private void createCountry(Scanner input) {
        System.out.println("Enter country:");
        input.nextLine();
        String countryName = input.nextLine();

        Country country = new Country(countryName);
        countryRepository.saveCountry(country);
    }
}
