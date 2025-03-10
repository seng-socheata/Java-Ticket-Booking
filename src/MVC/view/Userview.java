package MVC.view;
import MVC.model.User;

public class Userview {
    public static void displayUser(User user) {
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\n===== User Information =====");
        System.out.println("First Name  : " + user.getFirstname());
        System.out.println("Last Name   : " + user.getLastname());
        System.out.println("Username    : " + user.getUsername());
        System.out.println("Email       : " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("============================\n");
    }
}
