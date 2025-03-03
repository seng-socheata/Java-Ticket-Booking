package Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManagement {
    private static final List<movieSystem> movies = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public void runAdminPanel() {
        admin adminn = new admin();  // Corrected class name

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (admin.login(username, password)) {  // Corrected login method call
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
            System.out.println("0. Exit");  // Fixed exit option
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
        System.out.println("\nMovie List:");
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
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter duration: ");
        String duration = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter release date: ");
        String releaseDate = scanner.nextLine();

        movies.add(new movieSystem(id, title, genre, duration, price, releaseDate));  // Fixed class name
        System.out.println("Movie added successfully!");
        viewMovies();
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

        movieSystem movie = movies.get(index);
        System.out.print("Enter new title (leave blank to keep " + movie.getTitle() + "): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) movie.setTitle(title);

        System.out.print("Enter new genre (leave blank to keep current " + movie.getGenre() + "): ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) movie.setGenre(genre);

        System.out.print("Enter new duration (leave blank to keep current " + movie.getDuration() + "): ");
        String duration = scanner.nextLine();
        if (!duration.isEmpty()) movie.setDuration(duration);

        System.out.print("Enter new price (or -1 to keep current " + movie.getPrice() + "): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (price != -1) movie.setPrice(price);

        System.out.print("Enter new release date (leave blank to keep current " + movie.getReleaseDate() + "): ");
        String releaseDate = scanner.nextLine();
        if (!releaseDate.isEmpty()) movie.setReleaseDate(releaseDate);

        System.out.println("Movie updated successfully!");
        viewMovies();
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
        viewMovies();
    }
}
