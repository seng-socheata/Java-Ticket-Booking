package model.dao;


import model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{


    @Override
    public int addNewUser(User user) {
        String sql = """
                INSERT INTO "users" (username, first_name, last_name, email, phone_number, password_hash) VALUES (?,?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "tongeang"
                );
                PreparedStatement pre = connection.prepareStatement(sql)
                ) {
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getFirstName());
            pre.setString(3, user.getLastName());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getPhoneNumber());
            pre.setString(6, user.getPassword());
            int rowAffected = pre.executeUpdate();
            String message = rowAffected > 0 ? "Added user successfully" : "Cannot add user";
            System.out.println(message);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }
    @Override
    public int getAllUser(User user) {
        return 0;
    }
    @Override
    public int deleteUser(Integer id) {
        String sql = """
                DELETE "users" WHERE id = ?
                """;
        return 0;
    }

    @Override
    public int updateUser(Integer id) {
        return 0;
    }

    @Override
    public List<User> queryAllUsers() {
        return List.of();
    }
}
