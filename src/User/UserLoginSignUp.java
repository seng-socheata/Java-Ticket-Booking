package User;

import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.Scanner;
import static org.fusesource.jansi.Ansi.Color.*;

public class UserLoginSignUp {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private static HashMap<String, UserLoginSignUp> users = new HashMap<>();

    UserLoginSignUp(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    private static boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@email\\.com$", email);
    }

    private static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{8,}");
    }

    private static boolean isValidUsername(String username) {
        return !username.isEmpty() && !username.matches("\\d+");
    }

    //sign up
    public static void signUp() {
        Scanner input = new Scanner(System.in);

        String username;
        while (true) {
            System.out.print("\uD83D\uDC64 Username: ");
            username = input.nextLine().trim();
            if (isValidUsername(username)) break;
            System.out.println("Invalid username! It cannot be empty or contain only numbers. Please enter a valid username.");
        }

        String email;
        while (true) {
            System.out.print("\uD83D\uDCE9 Email: ");
            email = input.nextLine().trim();
            if (!email.isEmpty() && isValidEmail(email)) break;
            System.out.println("Invalid email! Please enter a valid email with @email.com.");

        }

        String phoneNumber;
        while (true) {
            System.out.print("\uD83D\uDCDE Phone Number:  (at least 8 digits): ");
            phoneNumber = input.nextLine().trim();
            if (!phoneNumber.isEmpty() && isValidPhoneNumber(phoneNumber)) break;
            System.out.println("Invalid phone number! It must be at least 8 digits.");
        }

        String password;
        while (true) {
            System.out.print("\uD83D\uDD11 Password: ");
            password = input.nextLine().trim();

            if (password.length() >= 8) break; // ✅ Ensure at least 8 characters

            System.out.println("❌ Password must be at least 8 characters long! Please try again.");
        }


        users.put(email, new UserLoginSignUp(username, email, phoneNumber, password));
        System.out.println("Sign-up successful! You can now log in.");
    }


    //login
    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        String email;

        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║               🔐 LOGIN PAGE                ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════╝").reset());
            System.out.print("\uD83D\uDCE9 Email: ");
            email = scanner.nextLine().trim();
            if (!email.isEmpty() && isValidEmail(email)) {
                if (users.containsKey(email)) break;
                System.out.println("Email not found! Please try again.");
            } else {
                System.out.println("Invalid email format! Please enter a valid email with @email.com.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("\uD83D\uDCDE Phone Number: ");
            phoneNumber = scanner.nextLine().trim();
            if (!phoneNumber.isEmpty() && users.get(email).getPhoneNumber().equals(phoneNumber)) break;
            System.out.println("Incorrect phone number! Please try again.");
        }



        String password;
        if (System.console() != null) {
            char[] passwordChars = System.console().readPassword("Password: ");
            password = new String(passwordChars);
        } else {
            StringBuilder passwordBuilder = new StringBuilder();
            System.out.print("Password: ");
            char ch;
            while (true) {
                ch = scanner.next().charAt(0);
                if (ch == '\n' || ch == '\r') break;
                passwordBuilder.append(ch);
                System.out.print("*");
            }
            System.out.println();
            password = passwordBuilder.toString();
        }


        if (users.get(email).getPassword().equals(password)) {
            UserLoginSignUp user = users.get(email);
            System.out.println(Ansi.ansi().fg(GREEN).a("\n╔══════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║               ✅ LOGIN SUCCESSFUL! 🎉            ║").reset());
            System.out.println(Ansi.ansi().fg(GREEN).a("╠══════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("║   Welcome back! You have successfully logged in. ║").reset());
            System.out.println(Ansi.ansi().fg(GREEN).a("╚══════════════════════════════════════════════════╝").reset());

            return true;
        } else {
            System.out.println("Incorrect password. Please try again.");
            return false;
        }
    }
}
