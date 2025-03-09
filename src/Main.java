//import User.SeatHall;
//import User.UserLoginSignUp;
//import User.MovieList.DisplayMovie;
//import Admin.MovieManagement;
//import org.fusesource.jansi.Ansi;
//import static org.fusesource.jansi.Ansi.Color.*;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        MovieManagement movieManagement = new MovieManagement();
//        Scanner scanner = new Scanner(System.in);
//        int option;
//
//        do {
//            System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ MAIN MENU ════════════════════════").reset());
//            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  User");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  Admin");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Exit");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());
//
//            option = getValidInput(scanner, "Enter your choice-> ");
//
//            switch (option) {
//                case 1:
//                    userMenu(scanner);
//                    break;
//                case 2:
//                    movieManagement.runAdminPanel();
//                    break;
//                case 0:
//                    System.out.println("Exiting program...");
//                    break;
//                default:
//                    System.out.println("Invalid option. Please choose again.");
//            }
//        } while (option != 0);
//
//        scanner.close();
//    }
//
//    // ✅ User Menu
//    private static void userMenu(Scanner scanner) {
//        int userOption;
//        do {
//            System.out.println(Ansi.ansi().fg(GREEN).a("\n════════════════════════ USER MENU ════════════════════════").reset());
//            System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════════════════════╗").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "1.  Sign Up");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-26s ║\n").reset()), "2.  Login");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════╣").reset());
//            System.out.printf(String.valueOf(Ansi.ansi().fg(RED).a("║ %-26s ║\n").reset()), "0.  Exit");
//            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════╝").reset());
//
//            userOption = getValidInput(scanner, "Choose an option: ");
//
//            switch (userOption) {
//                case 1:
//                    System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════╗").reset());
//                    System.out.println(Ansi.ansi().fg(YELLOW).a("║               🔐 SIGN UP PAGE              ║").reset());
//                    System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════╝").reset());
//                    UserLoginSignUp.signUp();
//                    if (UserLoginSignUp.login()) {
//                        loggedInMenu(scanner);
//                    }
//                    break;
//                case 2:
//                    if (UserLoginSignUp.login()) {
//                        loggedInMenu(scanner);
//                    }
//                    break;
//                case 0:
//                    System.out.println("Exiting User Menu...");
//                    break;
//                default:
//                    System.out.println("Invalid option. Please choose again.");
//            }
//        } while (userOption != 0);
//    }
//
//
//
//    // ✅ Logged-in User Menu
//    private static void loggedInMenu(Scanner scanner) {
//        int option;
//        SeatHall seatHall = new SeatHall();
//        do {
//            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════╗").reset());
//            System.out.println(Ansi.ansi().fg(YELLOW).a("║             🔐Logged-In Menu           ║").reset());
//            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════╣").reset());
//            System.out.println(Ansi.ansi().fg(CYAN).a("║   1️⃣     │ View Movies                 ║").reset());
//            System.out.println(Ansi.ansi().fg(GREEN).a("║   2️⃣     │ View Halls                  ║").reset());
//            System.out.println(Ansi.ansi().fg(MAGENTA).a("║   0️⃣     │ Exit System                 ║").reset());
//            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════╝").reset());
//
//            option = getValidInput(scanner, "Choose an option->  ");
//
//            switch (option) {
//                case 1:
//                    DisplayMovie.showMovies(false);
//                    break;
//                case 2:
//                    SeatHall.displaySeating();
//                    SeatHall.bookSeats();
//                    SeatHall.printReceipt();
//
//                    break;
//                case 0:
//                    System.out.println("Logging out...");
//                    break;
//                default:
//                    System.out.println("Invalid option. Please choose again.");
//            }
//        } while (option != 0);
//    }
//
//    // ✅ Method to ensure valid integer input (prevents string entry)
//    private static int getValidInput(Scanner scanner, String message) {
//        int input;
//        while (true) {
//            System.out.print(message);
//            if (scanner.hasNextInt()) {
//                input = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } else {
//                System.out.println("Invalid input! Please enter a valid number.");
//                scanner.next();
//            }
//        }
//        return input;
//    }
//}
//

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
                    System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════╗").reset());
                    System.out.println(Ansi.ansi().fg(YELLOW).a("║               🔐 SIGN UP PAGE              ║").reset());
                    System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════╝").reset());
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



    private static void loggedInMenu(Scanner scanner) {
        int option;
        SeatHall seatHall = new SeatHall();
        do {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║             🔐Logged-In Menu           ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║   1️⃣     │ View Movies                 ║").reset());
            System.out.println(Ansi.ansi().fg(GREEN).a("║   2️⃣     │ View Halls                  ║").reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a("║   0️⃣     │ Exit System                 ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════╝").reset());

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
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╔══════════════════════════════════════════╗"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("║           🎬 VIEW MOVIES MENU            ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╠══════════════════════════════════════════╣"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║  1️⃣  View Movies                         ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║  0️⃣  Back to Main Menu                   ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╚══════════════════════════════════════════╝"));

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
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╔══════════════════════════════════════════╗"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("║           🎭 VIEW HALL MENU              ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╠══════════════════════════════════════════╣"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║  1️⃣  View Hall                           ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║  0️⃣  Back to Main Menu                   ║"));
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╚══════════════════════════════════════════╝"));

            // Get valid input from the user
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