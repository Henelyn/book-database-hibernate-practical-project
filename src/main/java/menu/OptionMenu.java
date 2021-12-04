package menu;

import java.util.Scanner;

public class OptionMenu {
    boolean exit = false;
    private BookMenu bookMenu;
    private AuthorMenu authorMenu;
    private GenreMenu genreMenu;
    private PublisherMenu publisherMenu;
    private NationalityMenu nationalityMenu;
    private CountryMenu countryMenu;

    public OptionMenu() {
        this.bookMenu = new BookMenu();
        this.authorMenu = new AuthorMenu();
        this.countryMenu = new CountryMenu();
        this.genreMenu = new GenreMenu();
        this.nationalityMenu = new NationalityMenu();
        this.publisherMenu = new PublisherMenu();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Main menu ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Sub Menu - Book");
        System.out.println("2: Sub Menu - Author");
        System.out.println("3: Sub Menu - Publisher");
        System.out.println("4: Sub Menu - Genre");
        System.out.println("5: Sub Menu - Nationality");
        System.out.println("6: Sub Menu - Country");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while(!exit) {
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    this.bookMenu.menuChoice(input);
                    break;
                case 2:
                    this.authorMenu.menuChoice(input);
                    break;
                case 3:
                    this.publisherMenu.menuChoice(input);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    this.countryMenu.menuChoice(input);
                case 100:
                    exit = true;
                    System.out.println("System closed");
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }
        }
    }
}
