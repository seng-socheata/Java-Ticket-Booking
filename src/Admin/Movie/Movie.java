package Admin.Movie;


import java.util.*;

class Movie {
    String name;
    String time;
    double price;

    public Movie(String name, String time, double price) {
        this.name = name;
        this.time = time;
        this.price = price;
    }

    public void display() {
        System.out.println("Movie: " + name + " | Time: " + time + " | Price: $" + price);
    }
}

class Admin {
    private String username = "admin";
    private String password = "admin1234";

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }
}




