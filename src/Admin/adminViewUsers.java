package Admin;

import MVC.Config.Database;
import org.fusesource.jansi.Ansi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class adminViewUsers {
    public static void adminViewUser() {
        // SQL query to fetch user data, including password hash
        String sql = "SELECT first_name, last_name, username, email, phone_number, password_hash FROM users";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery()) {

            // Define initial column lengths based on headers
            int maxFirstNameLength = "First Name".length();
            int maxLastNameLength = "Last Name".length();
            int maxUsernameLength = "Username".length();
            int maxEmailLength = "Email".length();
            int maxPhoneNumberLength = "Phone Number".length();
            int maxPasswordLength = "Password_hash".length();

            // Calculate the maximum length of each column
            while (rs.next()) {
                maxFirstNameLength = Math.max(maxFirstNameLength, rs.getString("first_name").length());
                maxLastNameLength = Math.max(maxLastNameLength, rs.getString("last_name").length());
                maxUsernameLength = Math.max(maxUsernameLength, rs.getString("username").length());
                maxEmailLength = Math.max(maxEmailLength, rs.getString("email").length());
                maxPhoneNumberLength = Math.max(maxPhoneNumberLength, rs.getString("phone_number").length());
                maxPasswordLength = Math.max(maxPasswordLength, rs.getString("password_hash").length());
            }

            // Print the top of the table box
            System.out.println(Ansi.ansi().fg(GREEN).a("\n═════════════════════════════════════════════ USER LIST ═══════════════════════════════════════════").reset());
            System.out.println(Ansi.ansi().fg(GREEN).a(generateTableLine(maxFirstNameLength, maxLastNameLength, maxUsernameLength, maxEmailLength, maxPhoneNumberLength, maxPasswordLength)).reset());

            // Print table header
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-"+maxFirstNameLength+"s ║ %-"+maxLastNameLength+"s ║ %-"+maxUsernameLength+"s ║ %-"+maxEmailLength+"s ║ %-"+maxPhoneNumberLength+"s ║ %-"+maxPasswordLength+"s ║\n").reset()),
                    "First Name", "Last Name", "Username", "Email", "Phone Number", "Password Hash");

            // Print the table separator line after header
            System.out.println(Ansi.ansi().fg(BLUE).a(generateTableLine(maxFirstNameLength, maxLastNameLength, maxUsernameLength, maxEmailLength, maxPhoneNumberLength, maxPasswordLength)).reset());
            rs.beforeFirst();  // Reset the cursor to the start

            // Loop through the results and display user info
            while (rs.next()) {
                System.out.printf(String.valueOf(Ansi.ansi().fg(BLUE).a("║ %-"+maxFirstNameLength+"s ║ %-"+maxLastNameLength+"s ║ %-"+maxUsernameLength+"s ║ %-"+maxEmailLength+"s ║ %-"+maxPhoneNumberLength+"s ║ %-"+maxPasswordLength+"s ║\n").reset()),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("username"), rs.getString("email"), rs.getString("phone_number"), rs.getString("password_hash"));
            }

            // Closing table box
            System.out.println(Ansi.ansi().fg(BLUE).a(generateClosingLine(maxFirstNameLength, maxLastNameLength, maxUsernameLength, maxEmailLength, maxPhoneNumberLength, maxPasswordLength)).reset());


        } catch (Exception e) {
            // Error handling
            System.out.println("❌ Error occurred while fetching user data.");
            e.printStackTrace();  // For debugging purposes
        }
    }

    // Helper method to generate the table line based on column widths, including password hash
    private static String generateTableLine(int firstNameLength, int lastNameLength, int usernameLength, int emailLength, int phoneNumberLength, int passwordLength) {
        return "╔" +
                repeat("═", firstNameLength + 2) + "╦" +
                repeat("═", lastNameLength + 2) + "╦" +
                repeat("═", usernameLength + 2) + "╦" +
                repeat("═", emailLength + 2) + "╦" +
                repeat("═", phoneNumberLength + 2) + "╦" +
                repeat("═", passwordLength + 2) + "╗";
    }
    private static String generateClosingLine(int firstNameLength, int lastNameLength, int usernameLength, int emailLength, int phoneNumberLength, int passwordLength) {
        return "╚" +
                repeat("═", firstNameLength + 2) + "╩" +
                repeat("═", lastNameLength + 2) + "╩" +
                repeat("═", usernameLength + 2) + "╩" +
                repeat("═", emailLength + 2) + "╩" +
                repeat("═", phoneNumberLength + 2) + "╩" +
                repeat("═", passwordLength + 2) + "╝";
    }


    // Helper method to repeat a character for the table lines
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}