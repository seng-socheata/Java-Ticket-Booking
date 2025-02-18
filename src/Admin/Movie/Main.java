package Admin.Movie;

import java.util.*;
import java.util.ArrayList;

public class Main{
    private static List<Movie> movies = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Admin admin = new Admin();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!admin.login(username, password)) {
            System.out.println("Invalid credentials!");
            return;
        }
        System.out.println("Login successful! Welcome, Admin.");
        int choice;
        do {
            System.out.println("\n1. View Movies");
            System.out.println("2. Add Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMovies();
                    break;
                case 2:
                    addMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void viewMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
        for (int i = 0; i < movies.size(); i++) {
            System.out.print((i + 1) + ". ");
            movies.get(i).display();
        }
    }

    private static void addMovie() {
        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter movie time: ");
        String time = scanner.nextLine();
        System.out.print("Enter movie price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        movies.add(new Movie(name, time, price));
        System.out.println("Movie added successfully!");
    }

    private static void updateMovie() {
        viewMovies();
        if (movies.isEmpty()) return;
        System.out.print("Enter movie number to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= movies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.print("Enter new movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new movie time: ");
        String time = scanner.nextLine();
        System.out.print("Enter new movie price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        movies.set(index, new Movie(name, time, price));
        System.out.println("Movie updated successfully!");
    }

    private static void deleteMovie() {
        viewMovies();
        if (movies.isEmpty()) return;
        System.out.print("Enter movie number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= movies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        movies.remove(index);
        System.out.println("Movie deleted successfully!");
    }
}
