package Admin;

import User.MovieList.DisplayMovie;
import User.MovieList.Movie;
import org.fusesource.jansi.Ansi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static User.MovieList.DisplayMovie.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MovieManagement {
    private static final List<movieSystem> movies = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public void runAdminPanel() {
        admin adminn = new admin();  // Corrected class name

        while (true) {
            System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════════════╗").reset());
            System.out.println(Ansi.ansi().fg(YELLOW).a("║               🔐 LOGIN                     ║").reset());
            System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════════════╝").reset());

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
                case 0:
                    System.out.println(Ansi.ansi().fg(MAGENTA).a("🚪 Exiting System...").reset());
                    break;
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

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ MOVIE LIST ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(GREEN).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Release Date");
        System.out.println(Ansi.ansi().fg(BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬──────────────╣").reset());

        // Loop through all movies and display them
        for (Movie movie : DisplayMovie.movieList) {
            String durationFormatted = formattedDuration(movie.getDuration()); // Format duration properly
            String subtitleStatus = movie.hasSubtitle() ? "Khmer" : "English"; // Subtitle availability

            System.out.printf(String.valueOf(Ansi.ansi().fg(BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║ %-12s ║\n").reset()),
                    movie.getId(), movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getReleaseDate());
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩──────────────╝").reset());
    }
    private static void addMovie() {
        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║       🎬 ADD A NEW MOVIE           ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

        // Auto-generate unique Movie ID
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

        System.out.print(Ansi.ansi().fg(BLACK).a("📅 Enter Release Date (YYYY-MM-DD): ").reset());
        String releaseDate = scanner.nextLine();

        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no): ").reset());
        boolean hasSubtitle = scanner.nextLine().equalsIgnoreCase("yes");

        // Add new movie to the movie list (not coming soon list)
        DisplayMovie.movieList.add(new Movie(id, title, genre, duration, rating, hasSubtitle, releaseDate));

        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie added successfully!").reset());

        // Display only the updated Movie List (not coming soon)
        showMovies(true);
    }
    private static void updateMovie() {
        DisplayMovie.showMovies(true);
        if (!movies.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to update.").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║       ✏️ UPDATE MOVIE DETAILS      ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

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

//        System.out.print(Ansi.ansi().fg(MAGENTA).a("⏳ Enter new Duration in minutes (Current: " + selectedMovie.getDuration() + "): ").reset());
//        String durationInput = scanner.nextLine();
//        if (!durationInput.trim().isEmpty()) {
//            try {
//                int newDuration = Integer.parseInt(durationInput);
//                selectedMovie.setDuration(newDuration);
//            } catch (NumberFormatException e) {
//                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid duration format! Skipping update.").reset());
//            }
//        }
//
//        System.out.print(Ansi.ansi().fg(CYAN).a("⭐️ Enter new Rating (Current: " + selectedMovie.getRating() + "): ").reset());
//        String ratingInput = scanner.nextLine();
//        if (!ratingInput.trim().isEmpty()) {
//            try {
//                double newRating = Double.parseDouble(ratingInput);
//                selectedMovie.setRating(newRating);
//            } catch (NumberFormatException e) {
//                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid rating format! Skipping update.").reset());
//            }
//        }
//
//        System.out.print(Ansi.ansi().fg(GREEN).a("📅 Enter new Release Date (YYYY-MM-DD) (Current: " + selectedMovie.getReleaseDate() + "): ").reset());
//        String releaseDate = scanner.nextLine();
//        if (!releaseDate.trim().isEmpty()) {
//            selectedMovie.setReleaseDate(releaseDate);
//        }
//
//        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no, Current: " + (selectedMovie.hasSubtitle() ? "Yes" : "No") + "): ").reset());
//        String subtitleInput = scanner.nextLine();
//        if (!subtitleInput.trim().isEmpty()) {
//            selectedMovie.setSubtitle(subtitleInput.equalsIgnoreCase("yes"));
//        }
//
//        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie updated successfully!").reset());
//        viewMovies(); // Show updated movie list


//        System.out.print(Ansi.ansi().fg(GREEN).a("🎬 Enter new Title : " + movie.getTitle() + "): ").reset());
//        String title = scanner.nextLine();
//        if (!title.isEmpty()) movie.setTitle(title);
//
//        System.out.print(Ansi.ansi().fg(YELLOW).a("🎭 Enter new Genre (Leave blank to keep: " + movie.getGenre() + "): ").reset());
//        String genre = scanner.nextLine();
//        if (!genre.isEmpty()) movie.setGenre(genre);
//
//        System.out.print(Ansi.ansi().fg(MAGENTA).a("⏳ Enter new Duration (Leave blank to keep: " + movie.getDuration() + "): ").reset());
//        String duration = scanner.nextLine();
//        if (!duration.isEmpty()) movie.setDuration(duration);
//
//        double price;
//        while (true) {
//            System.out.print(Ansi.ansi().fg(CYAN).a("💰 Enter new Price (or -1 to keep: " + movie.getPrice() + "): ").reset());
//            if (scanner.hasNextDouble()) {
//                price = scanner.nextDouble();
//                scanner.nextLine();
//                if (price != -1) movie.setPrice(price);
//                break;
//            } else {
//                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a valid price.").reset());
//                scanner.nextLine();
//            }
//        }
//
//        System.out.print(Ansi.ansi().fg(GREEN).a("📅 Enter new Release Date (Leave blank to keep: " + movie.getReleaseDate() + "): ").reset());
//        String releaseDate = scanner.nextLine();
//        if (!releaseDate.isEmpty()) movie.setReleaseDate(releaseDate);
//
//        System.out.println(Ansi.ansi().fg(BLUE).a("\n✅ Movie updated successfully!").reset());
//        viewMovies();
//    }
        // Update each field (Allow blank input to keep the same value)
//        System.out.print(Ansi.ansi().fg(GREEN).a("🎬 Enter new Title (Current: " + selectedMovie.getTitle() + "): ").reset());
//        String title = scanner.nextLine();
//        if (!title.isEmpty()) {
//            selectedMovie.setTitle(title);
//        }
//
//        System.out.print(Ansi.ansi().fg(YELLOW).a("🎭 Enter new Genre (Current: " + selectedMovie.getGenre() + "): ").reset());
//        String genre = scanner.nextLine();
//        if (!genre.isEmpty()) {
//            selectedMovie.setGenre(genre);
//        }
//
//        System.out.print(Ansi.ansi().fg(MAGENTA).a("⏳ Enter new Duration (Current: " + selectedMovie.getDuration() + " mins): ").reset());
//        String durationInput = scanner.nextLine();
//        if (!durationInput.isEmpty()) {
//            try {
//                int newDuration = Integer.parseInt(durationInput);
//                selectedMovie.setDuration(newDuration);
//            } catch (NumberFormatException e) {
//                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid duration format! Skipping update.").reset());
//            }
//        }
//
//        System.out.print(Ansi.ansi().fg(CYAN).a("⭐️ Enter new Rating (Current: " + selectedMovie.getRating() + "): ").reset());
//        String ratingInput = scanner.nextLine();
//        if (!ratingInput.isEmpty()) {
//            try {
//                double newRating = Double.parseDouble(ratingInput);
//                selectedMovie.setRating(newRating);
//            } catch (NumberFormatException e) {
//                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid rating format! Skipping update.").reset());
//            }
//        }
//
//        System.out.print(Ansi.ansi().fg(GREEN).a("📅 Enter new Release Date (Current: " + selectedMovie.getReleaseDate() + "): ").reset());
//        String releaseDate = scanner.nextLine();
//        if (!releaseDate.isEmpty()){
//            selectedMovie.setReleaseDate(releaseDate);
//        }
//
//        System.out.print(Ansi.ansi().fg(BLACK).a("📺 Does it have subtitles? (yes/no, Current: " + (selectedMovie.hasSubtitle() ? "Yes" : "No") + "): ").reset());
//        String subtitleInput = scanner.nextLine();
//        if (!subtitleInput.isEmpty()) {
//            selectedMovie.setSubtitle(subtitleInput.equalsIgnoreCase("yes"));
//        }
//
//        System.out.println(Ansi.ansi().fg(GREEN).a("\n✅ Movie updated successfully!").reset());
//        viewMovies(); // Show updated movie list
    }
    private static void deleteMovie() {
        viewMovies();
        if (movies.isEmpty()) {
            System.out.println(Ansi.ansi().fg(RED).a("❌ No movies available to delete.").reset());
            return;
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("\n╔════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(RED).a("║        🗑 DELETE A MOVIE            ║").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════════════════════════════╝").reset());

        int index;
        while (true) {
            System.out.print(Ansi.ansi().fg(YELLOW).a("🔹 Enter Movie Number to Delete: ").reset());
            if (scanner.hasNextInt()) {
                index = scanner.nextInt() - 1;
                scanner.nextLine();
                if (index >= 0 && index < movies.size()) break;
                else System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid selection! Please enter a valid number.").reset());
            } else {
                System.out.println(Ansi.ansi().fg(RED).a("❌ Invalid input! Please enter a number.").reset());
                scanner.nextLine();
            }
        }

        movieSystem movie = movies.get(index);

        System.out.print(Ansi.ansi().fg(RED).a("\n⚠️ Are you sure you want to delete '"
                + movie.getTitle() + "'? (yes/no): ").reset());
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            movies.remove(index);
            System.out.println(Ansi.ansi().fg(GREEN).a("✅ Movie deleted successfully!").reset());
        } else {
            System.out.println(Ansi.ansi().fg(YELLOW).a("❌ Deletion canceled.").reset());
        }

        viewMovies();
    }


    // Method to get a valid integer
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
                scanner.next(); // Clear invalid input
            }
        }
    }

}