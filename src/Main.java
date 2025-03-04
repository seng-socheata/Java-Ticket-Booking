import Admin.AdminManagement;
import User.SeatHall;
import User.UserLoginSignUp;
import User.MovieList.DisplayMovie;


import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.Color.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminManagement adminManagement = new AdminManagement();
        int option;

        do {
            System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ MAIN MENU ════════════════════════").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  User");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  Admin");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Exit");
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());

            option = getValidInput(scanner, "Enter your choice-> ");

            switch (option) {
                case 1:
                    userMenu(scanner);
                    break;
                case 2:
                    AdminManagement.admin();
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

    // ✅ User Menu
    private static void userMenu(Scanner scanner) {
        int userOption;
        do {
            System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ USER MENU ════════════════════════").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  Sign Up");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  Login");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Exit");
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());

            userOption = getValidInput(scanner, "Choose an option: ");

            switch (userOption) {
                case 1:
                    System.out.println(Ansi.ansi().fg(BLUE).a("\n══════════════════════════════════════════").reset());
                    System.out.println(Ansi.ansi().fg(GREEN).a( "║               🔐 SIGN UP PAGE          ║").reset());
                    System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════╣").reset());
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



    // ✅ Logged-in User Menu
    private static void loggedInMenu(Scanner scanner) {
        int option;
        SeatHall seatHall = new SeatHall();
        do {
            System.out.println("\n---------------- Logged-In Menu ----------------");
            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  View Movies");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  View Halls");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Logout");
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());
            System.out.print("Choose an option: ");

            option = getValidInput(scanner, "Choose an option->  ");

            switch (option) {
                case 1:
                    DisplayMovie.showMovies();
                    break;
                case 2:
                    SeatHall.displaySeating();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 0);
    }

    // ✅ Method to ensure valid integer input (prevents string entry)
    private static int getValidInput(Scanner scanner, String message) {
        int input;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Discard invalid input
            }
        }
        return input;
    }
}
