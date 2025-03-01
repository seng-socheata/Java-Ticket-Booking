package Admin.MovieManageSystem;

import java.util.*;

class Movie {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private double price;
    private String releaseDate;

    public Movie(int id, String title, String genre, String duration, double price, String releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String setTitle){
        this.title = title;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String setGenre){
        this.genre = genre;
    }
    public String getDuration(){
        return duration;
    }
    public void setDuration(String setDuration){
        this.duration = duration;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void display() {
        System.out.println("Movie ID: " + id + " | Title: " + title + " | Genre: " + genre + " | Duration: " + duration + " | Price: $" + price + " | Release Date: " + releaseDate);
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

        movies.add(new Movie(id, title, genre, duration, price, releaseDate));
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

        Movie movie = movies.get(index);
        System.out.print("Enter new title (leave blank to keep " + movie.getTitle() + "): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) movie.setTitle(title);

        System.out.print("Enter new genre (leave blank to keep current " + movie.getGenre() + "): ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) movie.setGenre(genre);

        System.out.print("Enter new duration (leave blank to keep current" + movie.getDuration() +"): ");
        String duration = scanner.nextLine();
        if (!duration.isEmpty()) movie.setDuration(duration);

        System.out.print("Enter new price (or -1 to keep current " + movie.getPrice() + "): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (price != -1) movie.setPrice(price);

        System.out.print("Enter new release date (leave blank to keep current " + movie.getReleaseDate() + " ): ");
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
