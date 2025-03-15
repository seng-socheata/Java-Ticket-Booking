package Admin;

import MVC.Config.Database;
import User.MovieList.DisplayMovie;
import User.MovieList.Movie;
import User.SeatHall;
import org.fusesource.jansi.Ansi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import static User.MovieList.DisplayMovie.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MovieManagement {
    private static final List<movieSystem> movies = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public void runAdminPanel() {
        admin adminn = new admin();

        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                       🔐 LOGIN                         ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());
            System.out.print(Ansi.ansi().fg(CYAN).a("👤 Enter Username: ").reset());
            String username = new java.util.Scanner(System.in).nextLine();

            System.out.print(Ansi.ansi().fg(GREEN).a("🔑 Enter Password: ").reset());
            String password = new java.util.Scanner(System.in).nextLine();

            if (admin.login(username, password)) {
                System.out.println(Ansi.ansi().fg(GREEN).a("✅ Login successful! Welcome, Admin.").reset());
                break;
            }
            System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid credentials! Please try again.").reset());
        }


        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                    🎬 ADMIN MENU                       ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║                      1. Manage Movie                   ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║                      2. Manage Hall                    ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║                      0. Exit                           ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());


            int choice = getValidInt("📌 Enter your choice: ");
            switch (choice) {
                case 1:
                    showMovieManagementMenu();
                    break;
                case 2:
                    System.out.println(Ansi.ansi().fg(YELLOW).a("🏛 Managing Halls...").reset());
                    SeatHall.displaySeating();
                    SeatHall.resetSeating();

                    break;
                case 0:
                    System.out.println(Ansi.ansi().fg(RED).a("🚪 Exiting Admin Panel...").reset());
                    return;
                default:
                    System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid choice! Please try again.").reset());
            }
        }
    }


    private void showMovieManagementMenu() {
        int choice;
        do {

            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║                 🎬 MOVIE MANAGEMENT MENU               ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║          Option       │            Description         ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a(String.format("║   %-20s │ %-30s ║", "         1️⃣", "View Movies")).reset());
            System.out.println(Ansi.ansi().fg(GREEN).a(String.format("║   %-20s │ %-30s ║", "         2️⃣", "Add a New Movie")).reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a(String.format("║   %-20s │ %-30s ║", "         3️⃣", "Update Movie Details")).reset());
            System.out.println(Ansi.ansi().fg(RED).a(String.format("║   %-20s │ %-30s ║", "         4️⃣", "Delete a Movie")).reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a(String.format("║   %-20s │ %-30s ║", "         0️⃣", "Exit System")).reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

            choice = getValidInt("🔹 Enter your choice-> ");
            switch (choice) {
                case 1:
                    System.out.println(Ansi.ansi().fg(CYAN).a("📽 Viewing Movies...").reset());
                    viewMovies();
                    break;
                case 2:
                    System.out.println(Ansi.ansi().fg(GREEN).a("🎬 Adding a New Movie...").reset());
                    addMovie();
                    break;
                case 3:
                    System.out.println(Ansi.ansi().fg(YELLOW).a("✏️ Updating Movie Details...").reset());
                    updateMovie();
                    break;
                case 4:
                    System.out.println(Ansi.ansi().fg(RED).a("🗑 Deleting Movie...").reset());
                    deleteMovie();
                    break;
                case 0:
                    System.out.println(Ansi.ansi().fg(MAGENTA).a("🔙 Returning to Admin Menu...").reset());
                    return;
                default:
                    System.out.println(Ansi.ansi().fg(RED).a("⚠️ Invalid choice! Please try again.").reset());
            }
        } while (choice != 0);
    }



    private static void viewMovies() {
        if (DisplayMovie.movieList.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("\n❌ No movies available!").reset());
            return;
        }
        DisplayMovie.showMovies(true);
    }
    private static void addMovie() {
        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║                     🎬 ADD NEW MOVIE                   ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());


        int id = DisplayMovie.movieList.size() + 1;
        System.out.print(Ansi.ansi().fg(BLACK).a("🎬 Enter Movie Title: ").reset());
        String title = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(BLACK).a("🎭 Enter Genre: ").reset());
        String genre = scanner.nextLine();
        int duration;
        while (true) {
            System.out.print(Ansi.ansi().fg(BLACK).a("⏳ Enter Duration (minutes): ").reset());
            if (scanner.hasNextInt()) {
                duration = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a valid duration in minutes.").reset());
                scanner.nextLine();
            }
        }

        double rating;
        while (true) {
            System.out.print(Ansi.ansi().fg(BLACK).a("⭐️ Enter Movie Rating (e.g., 8.5): ").reset());
            if (scanner.hasNextDouble()) {
                rating = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a valid rating.").reset());
                scanner.nextLine();
            }
        }
        System.out.print(Ansi.ansi().fg(BLACK).a("🗣 Enter Movie Language: ").reset());
        String language = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(BLACK).a("📅 Enter Release Date (DD-MM-YYYY): ").reset());
        String releaseDate = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no): ").reset());
        boolean hasSubtitle = scanner.nextLine().equalsIgnoreCase("yes");

        DisplayMovie.movieList.add(new Movie(id, title, genre, duration, rating, hasSubtitle, language, releaseDate));
        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie added successfully!").reset());
        showMovies(true);
        addMovieToDB(title, genre, duration, rating, hasSubtitle, language, releaseDate);
    }
    public static void addMovieToDB(String title, String genre, int duration, double rating, boolean hasSubtitle, String language, String releaseDate ){
        String sql = "INSERT INTO movies (title, genre, duration, rating, language, release_date, subtitle) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String formattedDate = convertDateFormat(releaseDate);
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setInt(3, duration);
            stmt.setDouble(4, rating);
            stmt.setString(5, language);
            stmt.setDate(6, java.sql.Date.valueOf(formattedDate));
            stmt.setBoolean(7, hasSubtitle);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\n✅ Movie added successfully to Database!");
            } else {
                System.out.println("\n❌ Failed to add movie.");
            }
        } catch (SQLException e) {
            System.out.println("\n❌ Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static String convertDateFormat(String date) {
        try {
            String[] parts = date.split("-");
            return parts[2] + "-" + parts[1] + "-" + parts[0];  // Convert DD-MM-YYYY → YYYY-MM-DD
        } catch (Exception e) {
            System.out.println("❌ Error converting date: " + date);
            return "0000-00-00";
        }
    }


    private static void updateMovie() {
        DisplayMovie.showMovies(true);
        if (!movies.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to update.").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║                 ✏️ UPDATE MOVIE DETAILS                ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

        int movieID = getValidInt("🔹 Enter Movie ID to Update: ");

        Movie selectedMovie = null;
        for (Movie movie : DisplayMovie.movieList) {
            if (movie.getId() == movieID) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ Movie ID not found!").reset());
            return;
        }
        String currentDetailsLine = "║           🎬 Current Details          ║";

        // Define the movie details lines
        String[] lines = {
                "║ Title: " + selectedMovie.getTitle(),
                "║ Genre: " + selectedMovie.getGenre(),
                "║ Duration: " + selectedMovie.getDuration() + " minutes",
                "║ Rating: " + selectedMovie.getRating(),
                "║ Release Date: " + selectedMovie.getReleaseDate(),
                "║ Subtitles: " + (selectedMovie.hasSubtitle() ? "Yes" : "No")
        };

        // Get the length of the reference "Current Details" line
        int boxWidth = currentDetailsLine.length();

        // Create top and bottom borders based on this width
        String boxTopBottom = "╔" + "═".repeat(boxWidth - 2) + "╗";
        String boxBottom = "╚" + "═".repeat(boxWidth - 2) + "╝";
        String separator = "╠" + "═".repeat(boxWidth - 2) + "╣";

        // Print the top border (blue color)
        System.out.println(Ansi.ansi().fg(BLUE).a(boxTopBottom).reset());

        // Print the current details line (cyan color for the border, black text)
        System.out.println(Ansi.ansi().fg(CYAN).a(currentDetailsLine.substring(0, 1))  // Left border
                .reset().fg(BLACK).a(currentDetailsLine.substring(1, currentDetailsLine.length() - 1)) // Text in black
                .fg(CYAN).a(currentDetailsLine.charAt(currentDetailsLine.length() - 1)) // Right border
                .reset());

        // Print the separator line (blue color)
        System.out.println(Ansi.ansi().fg(BLUE).a(separator).reset());

        // Loop to print each line of movie details with padding
        for (String line : lines) {
            int spacesToAdd = boxWidth - line.length() - 2; // Space to the right to match box width
            String paddedLine = line + " ".repeat(spacesToAdd) + " ║";

            // Print the line with black text and blue border
            System.out.println(Ansi.ansi().fg(BLUE).a(paddedLine.substring(0, 1))  // Left border
                    .reset().fg(BLACK).a(paddedLine.substring(1, paddedLine.length() - 1)) // Text in black
                    .fg(BLUE).a(paddedLine.charAt(paddedLine.length() - 1)) // Right border
                    .reset());
        }

        // Print the bottom border (blue color)
        System.out.println(Ansi.ansi().fg(BLUE).a(boxBottom).reset());

        System.out.print(Ansi.ansi().fg(GREEN).a("🎬 Enter new Title (Current: " + selectedMovie.getTitle() + "): ").reset());
        String title = scanner.nextLine();
        if (!title.trim().isEmpty()) {
            selectedMovie.setTitle(title);
        }

        System.out.print(Ansi.ansi().fg(YELLOW).a("🎭 Enter new Genre (Current: " + selectedMovie.getGenre() + "): ").reset());
        String genre = scanner.nextLine();
        if (!genre.trim().isEmpty()) {
            selectedMovie.setGenre(genre);
        }

        System.out.print(Ansi.ansi().fg(MAGENTA).a("⏳ Enter new Duration (Current: " + selectedMovie.getDuration() + " minutes): ").reset());
        String durationInput = scanner.nextLine();
        if (!durationInput.trim().isEmpty()) {
            try {
                int newDuration = Integer.parseInt(durationInput);
                selectedMovie.setDuration(newDuration);
            } catch (NumberFormatException e) {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid duration format! Skipping update.").reset());
            }
        }

        System.out.print(Ansi.ansi().fg(CYAN).a("⭐️ Enter new Rating (Current: " + selectedMovie.getRating() + "): ").reset());
        String ratingInput = scanner.nextLine();
        if (!ratingInput.trim().isEmpty()) {
            try {
                double newRating = Double.parseDouble(ratingInput);
                selectedMovie.setRating(newRating);
            } catch (NumberFormatException e) {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid rating format! Skipping update.").reset());
            }
        }

        System.out.print(Ansi.ansi().fg(GREEN).a("📅 Enter new Release Date (Current: " + selectedMovie.getReleaseDate() + "): ").reset());
        String releaseDate = scanner.nextLine();
        if (!releaseDate.trim().isEmpty()) {
            selectedMovie.setReleaseDate(releaseDate);
        }

        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no, Current: " + (selectedMovie.hasSubtitle() ? "Yes" : "No") + "): ").reset());
        String subtitleInput = scanner.nextLine();
        if (!subtitleInput.trim().isEmpty()) {
            selectedMovie.setSubtitle(subtitleInput.equalsIgnoreCase("yes"));
        }
        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie updated successfully!").reset());
        DisplayMovie.viewMovies();
    }


    private static void deleteMovie() {
        viewMovies();

        if (DisplayMovie.movieList.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to delete.").reset());
            return;
        }
        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(RED).a("║                  🗑 DELETE A MOVIE                     ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════════════════╝").reset());

        int movieID = getValidInt("🔹 Enter Movie ID to delete: ");
        Movie selectedMovie = null;


        for (Movie movie : DisplayMovie.movieList) {
            if (movie.getId() == movieID) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ Movie ID not found!").reset());
            return;
        }

        System.out.print(Ansi.ansi().fg(RED).a("\n⚠️ Are you sure you want to delete '"
                + selectedMovie.getTitle() + "'? (yes/no): ").reset());
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            boolean removed = DisplayMovie.movieList.remove(selectedMovie);
            if (removed) {
                sortMoviesById();
                renumberMovieIds();
                System.out.println(Ansi.ansi().fg(GREEN).a("✅ Movie deleted successfully!").reset());
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Failed to delete the movie!").reset());
            }
        } else {
            System.out.println(Ansi.ansi().fg(YELLOW).a("❌ Deletion canceled.").reset());
        }
        viewMovies();
    }

    private static void sortMoviesById() {
        movieList.sort(Comparator.comparingInt(Movie::getId));
    }

    private static void renumberMovieIds() {
        for (int i = 0; i < DisplayMovie.movieList.size(); i++) {
            DisplayMovie.movieList.get(i).setId(i + 1);
        }
    }

    private static int getValidInt(String message) {
        int number;
        while (true) {
            System.out.print(Ansi.ansi().fg(BLACK).a(message).reset());
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return number;
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a valid number.").reset());
                scanner.next();
            }
        }
    }
}