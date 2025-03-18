

package User;

import org.fusesource.jansi.Ansi;
import MVC.Config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;
import static org.fusesource.jansi.Ansi.Color.*;

public class UserLoginSignUp {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private static HashMap<String, UserLoginSignUp> users = new HashMap<>();

    UserLoginSignUp( String username,String firstname, String lastname, String email, String phoneNumber, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getUsername() { return username; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }

    private static boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$", email);
    }
    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]{2,}$");
    }
    private static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{8,}");
    }

    private static boolean isValidUsername(String username) {
        return !username.isEmpty() && !username.matches("\\d+");
    }

    public static void signUp() {
        Scanner input = new Scanner(System.in);

        // First Name Validation
        String firstname;
        while (true) {
            System.out.print("\uD83D\uDC64 First Name: ");
            firstname = input.nextLine().trim();

            if (isValidName(firstname)) {
                break;
            }

            System.out.println("❌ Invalid first name! It must be at least 2 letters and contain only alphabets.");
        }

        // Last Name Validation
        String lastname;
        while (true) {
            System.out.print("\uD83D\uDC64 Last Name: ");
            lastname = input.nextLine().trim();

            if (isValidName(lastname)) {
                break;
            }

            System.out.println("❌ Invalid last name! It must be at least 2 letters and contain only alphabets.");
        }
        String username;
        while (true) {
            System.out.print("\uD83D\uDC64 Username: ");
            username = input.nextLine().trim();
            if (isValidUsername(username)) break;
            System.out.println("Invalid username! It cannot be empty or contain only numbers.");
        }
        String email;
        while (true) {
            System.out.print("\uD83D\uDCE9 Email: ");
            email = input.nextLine().trim();
            if (!email.isEmpty() && isValidEmail(email)) {
                if (isEmailExists(email)) {
                    System.out.println("❌ This email is already registered!");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid email! Please enter a valid email with @gmail.com.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("\uD83D\uDCDE Phone Number (8-10 digits only): ");
            phoneNumber = input.nextLine().trim();

            if (phoneNumber.length() >= 8 && phoneNumber.length() <= 10 && phoneNumber.chars().allMatch(Character::isDigit)) {
                break;
            }

            System.out.println("❌ Invalid phone number! Must be 8-10 digits and contain only numbers.");
        }

        String password;
        while (true) {
            System.out.print("\uD83D\uDD11 Password (at least 8 characters): ");
            password = input.nextLine().trim();
            if (password.length() >= 8) break;
            System.out.println("❌ Password must be at least 8 characters long! Please try again.");
        }

        if (saveUser(firstname, lastname, username, email, phoneNumber, password)) {
            System.out.println("✅ Sign-up successful! You can now log in.");
        } else {
            System.out.println("❌ Sign-up failed! Please try again.");
        }
    }

    private static boolean saveUser(String first_name, String last_name, String username, String email, String phone_number, String password_hash) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, phone_number, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, first_name);     // first_name
            stmt.setString(2, last_name);      // last_name
            stmt.setString(3, username);       // username
            stmt.setString(4, email);          // email
            stmt.setString(5, phone_number);  // phone_number
            stmt.setString(6, password_hash); // password_hash
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean login() {
        Scanner scanner = new Scanner(System.in);

        String email;
        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                      🔐 LOGIN PAGE                     ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());
            System.out.print("\uD83D\uDCE9 Email: ");
            email = scanner.nextLine().trim();

            if (!email.isEmpty() && isValidEmail(email)) {
                if (isEmailExists(email)) break;
                System.out.println("❌ Email not found! Please try again.");
            } else {
                System.out.println("❌ Invalid email format! Please enter a valid email with @email.com.");
            }
        }

        String password;
        while (true) {
            System.out.print("\uD83D\uDD11 Password: ");
            password = scanner.nextLine().trim();
            if (isPasswordCorrect(email, password)) break;
            System.out.println("❌ Incorrect password! Please try again.");
        }

        System.out.println(Ansi.ansi().fg(GREEN).a("\n╔════════════════════════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║                   ✅ LOGIN SUCCESSFUL! 🎉              ║").reset());
        System.out.println(Ansi.ansi().fg(GREEN).a("╚════════════════════════════════════════════════════════╝").reset());
        return true;
    }
    private static boolean isPhoneNumberCorrect(String email, String phone_number) {
        String sql = "SELECT phone FROM users WHERE email = ? AND phone_number = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, phone_number);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isPasswordCorrect(String email, String password) {
        String sql = "SELECT password_hash FROM users WHERE email = ? AND password_hash = ?"; // Change "password" to "password_hash"
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean isEmailExists(String email) {
        String sql = "SELECT email FROM users WHERE email = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
