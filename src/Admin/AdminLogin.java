package Admin;


import java.util.Scanner;

class Admin {
    private String username = "Tokata";
    private String password = "takatok";

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }
}

public class AdminLogin {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Admin admin = new Admin();

        while(true){
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (admin.login(username, password)) {
                System.out.println("Login successful! Welcome, Admin.");
                return;
            }
            System.out.println("Invalid credentials!");
        }

    }
}
