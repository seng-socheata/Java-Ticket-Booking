package Admin;



import java.util.Scanner;

public class Admin {

    private String username = "admin";
    private String password = "admin1234";  // You can change this for better security later

    public boolean login(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

