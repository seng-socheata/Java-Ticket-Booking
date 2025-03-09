package MVC;


import MVC.model.User;
import MVC.controller.UserController;


import java.util.Scanner;


public class MainD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\n===== User Management System =====");
            System.out.println("1. Add User");
            System.out.println("2. Get User by Email");
            System.out.println("3. Delete User");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter First Name: ");
                    String firstname = scanner.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastname = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    User newUser = new User(username, firstname, lastname, email, phoneNumber, password);
                    UserController.addUser(newUser);
                    break;

                case 2:
                    System.out.print("Enter Email to search: ");
                    String searchEmail = scanner.nextLine();
                    User foundUser = UserController.getUserByEmail(searchEmail);

                    if (foundUser != null) {
                        System.out.println("User Found: " + foundUser.getUsername());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Email to delete: ");
                    String deleteEmail = scanner.nextLine();
                    UserController.deleteUser(deleteEmail);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

