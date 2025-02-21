import User.SeatHall;
import User.UserLoginSignUp;
import User.MovieList.DisplayMovie;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.Color.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    userMenu(scanner);
                    break;
                case 2:

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
            System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ USER MENU ════════════════════════").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  Sign Up");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  Login");
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Exit");
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());

            System.out.print(Ansi.ansi().fg(CYAN).a("Choose an option: ").reset());

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            userOption = scanner.nextInt();
            scanner.nextLine();

            switch (userOption) {
                case 1:
                    UserLoginSignUp.signUp();  // ✅ Calls method from User package
                    System.out.println(Ansi.ansi().fg(BLUE).a("\n══════════════════════════════════════════").reset());
                    System.out.println(Ansi.ansi().fg(YELLOW).a( "║               🔐 LOGIN PAGE           ║").reset());
                    System.out.println(Ansi.ansi().fg(BLUE).a("╠══════════════════════════════════════════╣").reset());
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

    private static void loggedInMenu(Scanner scanner) {
        int option;
        SeatHall seatHall = new SeatHall();
        do {
            System.out.println("\n---------------- Logged-In Menu ----------------");
            System.out.println(" 1. View Movies");
            System.out.println(" 2. View Halls");
            System.out.println(" 0. Logout");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    DisplayMovie.showMovies();
                    break;
                case 2:
                    boolean viewingSeats = true;
                    while (viewingSeats) {
                        SeatHall.displaySeating(); // Call the display method
                        System.out.print("Do you want to view the seating again? (yes/no): ");
                        String choice = scanner.nextLine().trim().toLowerCase();
                        if (choice.equals("no")) {
                            viewingSeats = false; // Exit the loop
                        }
                    }
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 0);
    }
}
