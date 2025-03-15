
import Design.ThreeDcolor;
import User.SeatHall;
import User.UserLoginSignUp;
import User.MovieList.DisplayMovie;
import Admin.MovieManagement;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.Color.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement movieManagement = new MovieManagement();
        Scanner scanner = new Scanner(System.in);
        int option;
        ThreeDcolor.displayTitle();
        do {
            printCenteredMenu("MAIN MENU", new String[]{
                    Ansi.ansi().fg(YELLOW).a("1. User").reset().toString(),
                    Ansi.ansi().fg(YELLOW).a("2. Admin").reset().toString(),
                    Ansi.ansi().fg(RED).a("0. Exit").reset().toString()
            });

            option = getValidInput(scanner, "Enter your choice-> ");

            switch (option) {
                case 1:
                    userMenu(scanner);
                    break;
                case 2:
                    movieManagement.runAdminPanel();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 0);

        scanner.close();
    }

    private static void userMenu(Scanner scanner) {
        int userOption;

        do {

            printCenteredMenu("USER MENU", new String[]{
                    Ansi.ansi().fg(YELLOW).a("1. Sign Up").reset().toString(),
                    Ansi.ansi().fg(YELLOW).a("2. Login").reset().toString(),
                    Ansi.ansi().fg(RED).a("0. Exit").reset().toString()
            });

            userOption = getValidInput(scanner, "Choose an option: ");

            switch (userOption) {
                case 1:
                    System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
                    System.out.println(Ansi.ansi().fg(YELLOW).a("║                     🔐 SIGN UP PAGE                    ║").reset());
                    System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());
                    UserLoginSignUp.signUp();
                    if (UserLoginSignUp.login()) {
                        loggedInMenu(scanner);
                    }
                    break;
                case 2:
                    if (UserLoginSignUp.login()) {
                        loggedInMenu(scanner);
                    }
                    break;
                case 0:
                    System.out.println("Exiting User Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (userOption != 0);
    }
    private static void printCenteredMenu(String title, String[] options) {
        int totalWidth = 56;
        String border = "════════════════════════════════════════════════════════";

        System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ " + title + " ════════════════════════").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╔" + border + "╗").reset());

        for (int i = 0; i < options.length; i++) {
            String text = options[i];
            String plainText = text.replaceAll("\u001B\\[[;\\d]*m", ""); // Remove ANSI color codes
            int padding = (totalWidth - plainText.length()) / 2;
            String formattedLine = Ansi.ansi().fg(BLUE).a("║").reset()
                    + " ".repeat(padding) + text + " ".repeat(totalWidth - plainText.length() - padding)
                    + Ansi.ansi().fg(BLUE).a("║").reset();
            System.out.println(formattedLine);

            if (i < options.length - 1) {
                System.out.println(Ansi.ansi().fg(BLUE).a("╠" + border + "╣").reset());
            }
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("╚" + border + "╝").reset());
    }
    private static void loggedInMenu(Scanner scanner) {
        int option;
        SeatHall seatHall = new SeatHall();
        do {

            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                   🎬 MOVIE MENU                        ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║          Option       │            Description         ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a(String.format("║   %-20s │ %-30s ║", "         1️⃣", "View Movies")).reset());
            System.out.println(Ansi.ansi().fg(GREEN).a(String.format("║   %-20s │ %-30s ║", "         2️⃣", "View Halls")).reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a(String.format("║   %-20s │ %-30s ║", "         0️⃣", "Exit System")).reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

            option = getValidInput(scanner, "Choose an option->  ");

            switch (option) {
                case 1:
                    viewMoviesMenu(scanner);
                    break;
                case 2:
                    viewHallMenu(scanner);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 0);
    }

    public static void viewMoviesMenu(Scanner scanner) {
        int movieOption;
        do {

            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                   🎬 VIEW MOVIES MENU                  ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║          Option       │            Description         ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a(String.format("║   %-20s │ %-30s ║", "         1️⃣", "View Movies")).reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a(String.format("║   %-20s │ %-30s ║", "         0️⃣", "Back to Main Menu")).reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

            // Get valid input from the user
            movieOption = getValidInput(scanner, "Choose an option -> ");

            switch (movieOption) {
                case 1:
                    DisplayMovie.showMovies(false);  // Show movies
                    break;

                case 0:
                    System.out.println("Returning to the main menu...");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }

        } while (movieOption != 0);
    }

    public static void viewHallMenu(Scanner scanner) {
        int hallOption;
        do {

            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                   🎭 VIEW HALL MENU                    ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║          Option       │            Description         ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a(String.format("║   %-20s │ %-30s ║", "         1️⃣", "View Hall")).reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a(String.format("║   %-20s │ %-30s ║", "         0️⃣", "Back to Main Menu")).reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

            hallOption = getValidInput(scanner, "Choose an option -> ");

            switch (hallOption) {
                case 1:
                    SeatHall.displaySeating();
                    SeatHall.bookSeats();
                    SeatHall.printReceipt();
                    break;

                case 0:
                    System.out.println("Returning to the main menu...");
                    break;

                default:
                    System.out.println("❌ Invalid option. Please choose again.");
                    break;
            }

        } while (hallOption != 0);
    }

    private static int getValidInput(Scanner scanner, String message) {
        int input;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
        return input;
    }
}