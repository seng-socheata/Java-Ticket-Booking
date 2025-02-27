package Admin.MovieManageSystem;

import java.util.*;

class Movie {
    private final int id;
    private final String title;
    private final String genre;
    private final int duration;
    private final double price;
    private final String releaseDate;

    public Movie(int id, String title, String genre, int duration, double price, String releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public void display() {
        System.out.println("Movie ID: " + id + " | Movie title: " + title + " | Price: $" + price + " | Movie release date: " + releaseDate);
    }
}

class Admin {
    private final String username = "Mike";
    private final String password = "hashi18";

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }
}

public class MovieManagementSystem {
    private static final List<Movie> movies = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Admin admin = new Admin();

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (admin.login(username, password)) {
                System.out.println("Login successful! Welcome, Admin.");
                break;
            }
            System.out.println("Invalid credentials!");
        }

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
        System.out.print("Enter movie ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Genre: "); // Fix spelling
        String genre = scanner.nextLine();
        System.out.print("Duration: ");
        int duration = scanner.nextInt();
        System.out.print("Enter movie price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Movie release date: ");
        String releaseDate = scanner.nextLine();

        movies.add(new Movie(id, title, genre, duration, price, releaseDate));
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

        System.out.print("Enter new movie ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new movie title: ");
        String title = scanner.nextLine();
        System.out.print("Genre: "); // Fix spelling
        String genre = scanner.nextLine();
        System.out.print("Duration: ");
        int duration = scanner.nextInt();
        System.out.print("Enter new movie price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Movie release date: ");
        String releaseDate = scanner.nextLine();

        // Fix: Replace existing movie instead of adding a new one
        movies.set(index, new Movie(id, title, genre, duration, price, releaseDate));
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
