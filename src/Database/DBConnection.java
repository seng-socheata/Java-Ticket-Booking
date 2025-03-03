package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "seng99";

    public static void main(String[] args) {
        insertUser("John Doe", "John", "Doe", "johndoe@email.com", "1234567890", "hashed_password");
    }

    public static void insertUser(String name, String firstName, String lastName, String email, String phone, String password) {
        String sql = "INSERT INTO users (name, first_name, last_name, email, phone_number, password_hash) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ User Inserted Successfully!");
            } else {
                System.out.println("❌ Failed to Insert User!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
