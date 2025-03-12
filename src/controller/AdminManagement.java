package controller;

import org.fusesource.jansi.Ansi;
import view.MovieList.DisplayMovie;
import view.MovieList.Movie;
import view.UserLoginSignUp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static view.MovieList.DisplayMovie.movieList;
import static view.MovieList.DisplayMovie.showMovies;

public class AdminManagement {
    private static final List<movieSystem> movies = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public void runAdminPanel() {
        admin adminn = new admin();  // Corrected class name

        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║               🔐 LOGIN                     ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════╝").reset());

            System.out.print(Ansi.ansi().fg(CYAN).a("👤 Enter Username: ").reset());
            String username = new Scanner(System.in).nextLine();

            System.out.print(Ansi.ansi().fg(GREEN).a("🔑 Enter Password: ").reset());
            String password = new Scanner(System.in).nextLine();

            if (admin.login(username, password)) {
                System.out.println(Ansi.ansi().fg(GREEN).a("✅ Login successful! Welcome, Admin.").reset());
                break;
            }
            System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid credentials! Please try again.").reset());
        }

        int choice;
        do {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║        🎬 MOVIE MANAGEMENT MENU        ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║  Option  │        Description          ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╠════════════════════════════════════════╣").reset());
            System.out.println(Ansi.ansi().fg(CYAN).a("║   1️⃣     │ View Movies                 ║").reset());
            System.out.println(Ansi.ansi().fg(GREEN).a("║   2️⃣     │ Add a New Movie             ║").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║   3️⃣     │ Update Movie Details        ║").reset());
            System.out.println(Ansi.ansi().fg(RED).a("║   4️⃣     │ Delete a Movie              ║").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║   5️⃣     │ View Users                  ║").reset());
//            System.out.println(Ansi.ansi().fg(BLUE).a("║   6️⃣     │ View Hall (Seats)           ║").reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a("║   7️⃣     │ View Booking                ║").reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a("║   0️⃣     │ Exit System                 ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════╝").reset());

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
                case 5:
                    System.out.println(Ansi.ansi().fg(YELLOW).a("📜 Viewing Registered Users...").reset());
                    viewUsers();
                    break;
                case 6:
                    System.out.println(Ansi.ansi().fg(BLUE).a(" View Booking...").reset());
                    viewHall();
                    break;
                case 0:
                    System.out.println(Ansi.ansi().fg(MAGENTA).a("🚪 Exiting System...").reset());
                    break;
                default:
                    System.out.println(Ansi.ansi().fg(RED).a("⚠️ Invalid choice! Please try again.").reset());
            }
        } while (choice != 0);
    }
    private static void viewMovies() {
        if (movieList.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("\n❌ No movies available!").reset());
            return;
        }


        showMovies(true);
    }
    private static void addMovie() {
        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║       🎬 ADD A NEW MOVIE           ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

        // Auto-generate unique Movie ID
        int id = movieList.size() + 1;

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

        System.out.print(Ansi.ansi().fg(BLACK).a("📅 Enter Release Date (DD-MM-YYYY): ").reset());
        String releaseDate = scanner.nextLine();

        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no): ").reset());
        boolean hasSubtitle = scanner.nextLine().equalsIgnoreCase("yes");

        // Add new movie to the movie list (not coming soon list)
        movieList.add(new Movie(id, title, genre, duration, rating, hasSubtitle, releaseDate));

        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie added successfully!").reset());

        // Display only the updated Movie List (not coming soon)
        showMovies(true);
    }
    private static void updateMovie() {
        showMovies(true);
        if (!movies.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to update.").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║       ✏️ UPDATE MOVIE DETAILS      ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

        int movieID = getValidInt("🔹 Enter Movie ID to Update: ");


        Movie selectedMovie = null;
        for (Movie movie : movieList) {
            if (movie.getId() == movieID) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ Movie ID not found!").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(CYAN).a("🎬 Current Details:").reset());
        System.out.println("Title: " + selectedMovie.getTitle());
        System.out.println("Genre: " + selectedMovie.getGenre());
        System.out.println("Duration: " + selectedMovie.getDuration() + " minutes");
        System.out.println("Rating: " + selectedMovie.getRating());
        System.out.println("Release Date: " + selectedMovie.getReleaseDate());
        System.out.println("Subtitles: " + (selectedMovie.hasSubtitle() ? "Yes" : "No"));


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

        // Confirmation message for the successful update
        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie updated successfully!").reset());

        // Show the updated movie list
        DisplayMovie.viewMovies();


    }


    private static void deleteMovie() {
        viewMovies();


        if (DisplayMovie.movieList.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to delete.").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(RED).a("║        🗑 DELETE A MOVIE           ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

        int movieID = getValidInt("🔹 Enter Movie ID to delete: "); // Get movie ID to delete
        Movie selectedMovie = null;

        // Find the movie with the given ID
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

    private void viewUsers() {
        if (UserLoginSignUp.users.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No registered users found!").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(RED).a("║         📜 REGISTERED USERS          ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());


        for (UserLoginSignUp user : UserLoginSignUp.users.values()) {
            System.out.println(Ansi.ansi().fg(CYAN).a("👤 Username: " + user.getUsername()).reset());
            System.out.println(Ansi.ansi().fg(GREEN).a("📧 Email: " + user.getEmail()).reset());
            System.out.println(Ansi.ansi().fg(MAGENTA).a("📞 Phone: " + user.getPhoneNumber()).reset());
        }
    }

    private void viewHall() {
        final int rows = 10;
        final int cols = 10;
        String[][] seats = new String[rows + 1][cols];

        char rowLabel = 'J';


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = rowLabel + String.valueOf(j + 1) + "-AV"; // AV = Available
            }
            rowLabel--;
        }


        for (int j = 0; j < cols; j += 2) {
            if (j + 1 < cols) {
                seats[rows][j] = "VIP" + (j + 1) + "-VIP" + (j + 2);
                seats[rows][j + 1] = "";
            }
        }


        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║                   🎬 CINEMA HALL                  ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println("                  🎥 SCREEN THIS WAY 🎥\n");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(seats[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static int getValidInt(String message) {
        while (true) {
            System.out.print(Ansi.ansi().fg(BLACK).a(message).reset());
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a valid number.").reset());
                scanner.next();
            }
        }
    }
}

