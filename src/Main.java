import User.UserLoginSignUp;
import User.MovieList.DisplayMovie;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=====================|  Main Menu |===================");
            System.out.println(" 1.  User");
            System.out.println(" 2.  Admin");
            System.out.println(" 0.  Exit");
            System.out.print("Choose an option: ");

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
            System.out.println("\n---------------- User Menu ----------------");
            System.out.println(" 1. Sign Up");
            System.out.println(" 2. Login");
            System.out.println(" 0. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            userOption = scanner.nextInt();
            scanner.nextLine();

            switch (userOption) {
                case 1:
                    UserLoginSignUp.signUp();  // ✅ Calls method from User package
                    System.out.println("\n ____________________________________________");
                    System.out.println("\n |                 Login                     |");
                    System.out.println("\n |___________________________________________|");
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
        do {
            System.out.println("\n---------------- Logged-In Menu ----------------");
            System.out.println(" 1. View Movies");
            System.out.println(" 2. Book a Seat");
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
                    DisplayMovie.showMovies();  // ✅ Calls method from MovieList package
                    break;
                case 2:
                    System.out.println("Booking Seat (Feature to be implemented)");
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
