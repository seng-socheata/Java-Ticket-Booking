package Admin;



import User.MovieList.DisplayMovie;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter admin username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String enteredPassword = scanner.nextLine();

        // Check login credentials
        if (admin.login(enteredUsername, enteredPassword)) {
            System.out.println("Login successful!");

            viewMovies();
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }

    public static void viewMovies() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n********* Admin Menu *********");
            System.out.println("1. View Movies");
            System.out.println("2. Add Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Viewing all movies...");
                    DisplayMovie.showMovies(true);
                    break;
                case 2:
                    System.out.println("Adding a new movie...");
                    // Call the method to add a movie
                    break;
                case 3:
                    System.out.println("Updating an existing movie...");
                    // Call the method to update a movie
                    break;
                case 4:
                    System.out.println("Deleting a movie...");
                    // Call the method to delete a movie
                    break;
            }
        } while (choice != 5);
    }
}

