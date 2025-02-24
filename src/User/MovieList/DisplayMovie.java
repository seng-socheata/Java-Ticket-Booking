package User.MovieList;


import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import java.util.ArrayList;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;

public class DisplayMovie {

    private static ArrayList<Movie> movieList = new ArrayList<>();
    private static ArrayList<Movie> comingSoonMovies = new ArrayList<>();


    static {

        movieList.add(new Movie(1, "Avatar", "Sci-Fi", 134, 8.1, true, "18-02-2025"));
        movieList.add(new Movie(2, "Inception", "Action", 148, 8.8, true, "17-02-2025"));
        movieList.add(new Movie(3, "Titanic", "Romance", 95, 7.8, false, "15-02-2025"));
        movieList.add(new Movie(4, "Interstellar", "Sci-Fi", 169, 8.6, true, "15-02-2025"));
        movieList.add(new Movie(5, "Joker", "Thriller", 122, 8.4, false, "17-02-2025"));
        movieList.add(new Movie(6,"14th February","Horror",80,5.0,true,"18-02-2025"));
        movieList.add(new Movie(7,"Oddity","Horror",93,5.9,false,"14-02-2025"));
        movieList.add(new Movie(8, "Firefighter","Action",104,5.8,true,"07-02-2025"));
        movieList.add(new Movie(9,"Her Story","Comedy",123,6.0,false,"11-02-2025"));
        movieList.add(new Movie(10,"Undertaker","Horror",80,7.8,true,"06-02-2025"));

        // Coming Soon Movies
        comingSoonMovies.add(new Movie(11, "Guardians", "Sci-Fi", 150, 9.0, true, "01-03-2025"));
        comingSoonMovies.add(new Movie(12, "The Flash", "Action", 145, 8.3, true, "10-03-2025"));
        comingSoonMovies.add(new Movie(13, "Avatar 2", "Sci-Fi", 165, 8.9, true, "15-03-2025"));
        comingSoonMovies.add(new Movie(14, " Impossible 7", "Action", 130, 8.5, false, "20-03-2025"));
        comingSoonMovies.add(new Movie(15,"Home Sweet Hell","Horror",100,4.0,true,"25-02-2025"));
    }


    public static void showMovies() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ MOVIE LIST ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(GREEN).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-10s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Release Date");
        System.out.println(Ansi.ansi().fg(BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬──────────────╣").reset());

        // 🎥 Display Movies
        for (Movie movie : movieList) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? "Khmer" : "English";

            System.out.printf(String.valueOf(Ansi.ansi().fg(BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-10s ║ %-12s ║\n").reset()),
                    movie.getId(), movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getReleaseDate());
        }
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩──────────────╝").reset());







        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("\n══════════════════════ COMING SOON MOVIES ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Release Date");
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬──────────────╣").reset());

        // 🎥 Display Coming Soon Movies
        for (Movie movie : comingSoonMovies) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? " Khmer" : " English";

            // Print each movie in the table with a colored format
            System.out.printf(String.valueOf(Ansi.ansi().fg(Ansi.Color.BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║ %-12s ║\n").reset()),
                    movie.getId(), movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getReleaseDate());
        }

        // Table Footer for Coming Soon Movies
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩──────────────╝").reset());

        // 🎟️ Select Movie (With Validation)
        int movieId;
        while (true) {
            System.out.print("Choose a movie by ID: ");
            movieId = scanner.nextInt();
            if (movieId >= 1 && movieId <= movieList.size()) {
                break;
            }
            System.out.println("❌ Invalid selection! Please choose an available movie.");
        }


// 📅 Choose Date
        String[] dates = {"Today (18)", "Tue (19)", "Wed (20)", "Thurs (21)"};

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ AVAILABLE DATES ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════╦════════════╦════════════╦════════════╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║\n").reset()),
                "Today (18)", "Tue (19)", "Wed (20)", "Thurs (21)");
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════╩════════════╩════════════╩════════════╝").reset());

        System.out.print("Choose Date (Enter number 1-4): ");
        int dateChoice = scanner.nextInt();

        System.out.println("\n🎬 You have selected: " + movieList.get(movieId - 1).getTitle());
        System.out.println("📅 Date: " + dates[dateChoice - 1]);


// 📍 Choose Location & Time
        String[][] locations = {
                {"Mean Chey", "7:30 AM", "10:30 AM", "12:45 PM","6:00 PM","9:30 PM"},
                {"City Mall", "8:00 AM", "1:30 PM", "3:45 PM","4:45 PM","7:30 PM"},
                {"Aeon 2", "9:00 AM", "2:15 PM", "4:20 PM","5:50 PM","10:20 PM"},
                {"Aeon 1", "9:45 AM", "10:42 PM", "5:00 PM","8:50 PM","10:00 PM "}
        };

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ AVAILABLE LOCATIONS & TIMES ══════════════════════").reset());
        System.out.println("╔════════════╦════════════╦════════════╦════════════╦════════════╦════════════╗");
        System.out.printf(" ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║\n",
                "Location", "Time 1", "Time 2", "Time 3", "Time 4", "Time 5");
        System.out.println("╠════════════╬════════════╬════════════╬════════════╬════════════╬════════════╣");

// Display locations
        for (String[] location : locations) {
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║\n").reset()),
                    location[0], location[1], location[2], location[3], location[4], location[5]);
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════╩════════════╩════════════╩════════════╝").reset());

        System.out.print("Choose Location (Enter number 1-4): ");
        int locationChoice = scanner.nextInt();

        System.out.println("\n📍 Location: " + locations[locationChoice - 1][0]);

        System.out.print("Choose Time Slot (Enter 1-5): ");
        int timeChoice = scanner.nextInt();

        System.out.println("\n🕒 Time: " + locations[locationChoice - 1][timeChoice]);
        System.out.println("You Choose Successfully..");

    }
    private static String formattedDuration(int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;
        return String.format("%dh %02dm", hours, minutes);
    }

}