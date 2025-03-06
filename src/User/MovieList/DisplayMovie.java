package User.MovieList;


import User.SeatHall;
import User.UserSelection;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.Scanner;

import static User.UserSelection.selectedMovie;
import static org.fusesource.jansi.Ansi.Color.*;

public class DisplayMovie {

    public static ArrayList<Movie> movieList = new ArrayList<>();
    private static ArrayList<Movie> comingSoonMovies = new ArrayList<>();


    static {

        movieList.add(new Movie(1, "Avatar", "Sci-Fi", 134, 8.1, true,"France", "18-02-2025"));
        movieList.add(new Movie(2, "Inception", "Action", 148, 8.8, true, "China","17-02-2025"));
        movieList.add(new Movie(3, "Titanic", "Romance", 95, 7.8, false,"India", "15-02-2025"));
        movieList.add(new Movie(4, "Interstellar", "Sci-Fi", 169, 8.6, true,"Thailand", "15-02-2025"));
        movieList.add(new Movie(5, "Joker", "Thriller", 122, 8.4, false, "English","17-02-2025"));
        movieList.add(new Movie(6,"14th February","Horror",80,5.0,true,"Khmer","18-02-2025"));
        movieList.add(new Movie(7,"Oddity","Horror",93,5.9,false,"Korea","14-02-2025"));
        movieList.add(new Movie(8, "Firefighter","Action",104,5.8,true,"Spanish","07-02-2025"));
        movieList.add(new Movie(9,"Her Story","Comedy",123,6.0,false,"Chinese","11-02-2025"));
        movieList.add(new Movie(10,"Undertaker","Horror",80,7.8,true,"Khmer","06-02-2025"));

        // Coming Soon Movies
        comingSoonMovies.add(new Movie(11, "Guardians", "Sci-Fi", 150, 9.0, true, "Chinese","01-03-2025"));
        comingSoonMovies.add(new Movie(12, "The Flash", "Action", 145, 8.3, true, "Korea","10-03-2025"));
        comingSoonMovies.add(new Movie(13, "Avatar 2", "Sci-Fi", 165, 8.9, true, "Khmer","15-03-2025"));
        comingSoonMovies.add(new Movie(14, " Impossible 7", "Action", 130, 8.5, false, "Thailand","20-03-2025"));
        comingSoonMovies.add(new Movie(15,"Home Sweet Hell","Horror",100,4.0,true,"Vietnames","25-02-2025"));
    }


    public static void showMovies(boolean isAdminVeiw) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ MOVIE LIST ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(GREEN).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦───────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Language", "Release Date");
        System.out.println(Ansi.ansi().fg(BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬───────────╬──────────────╣").reset());

        // 🎥 Display Movies
        for (Movie movie : movieList) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? "Khmer" : "English";

            System.out.printf(String.valueOf(Ansi.ansi().fg(BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                    movie.getId(), movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getLanguage(),movie.getReleaseDate());
        }
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩───────────╩──────────────╝").reset());




        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("\n══════════════════════ COMING SOON MOVIES ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦───────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Language", "Release Date");
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬───────────╬──────────────╣").reset());

        // 🎥 Display Coming Soon Movies
        int comingsoonID=1;
        for (Movie movie : comingSoonMovies) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? " Khmer" : " English";

            // Print each movie in the table with a colored format
            System.out.printf(String.valueOf(Ansi.ansi().fg(Ansi.Color.BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                    comingsoonID++, movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getLanguage(),movie.getReleaseDate());
        }

        // Table Footer for Coming Soon Movies
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩───────────╩──────────────╝").reset());

        if (isAdminVeiw) {
            return;
        }
        // 🎟️ Select Movie (With Validation)
        int movieId;
        while (true) {
            System.out.print("Choose a movie by ID -> ");
            movieId = scanner.nextInt();
            if (movieId >= 1 && movieId <= movieList.size()) {
                break;
            }
            System.out.println("❌ Invalid selection! Please choose an available movie.");
        }


// 📅 Choose Date
        String[] dates = {"Today (18)", "Tue (19)"};

        System.out.println(Ansi.ansi().fg(GREEN).a("\n══════════════════════ AVAILABLE DATES ══════════════════════").reset());
        System.out.println(Ansi.ansi().fg(BLUE).a("╔════════════╦════════════╗").reset());
        System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ \n").reset()),
                "Today (18)", "Tue (19)");
        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════╩════════════╝").reset());

        System.out.print("Choose Date (Enter number 1-2)-> ");
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
        System.out.println(Ansi.ansi().fg(MAGENTA).a("╔════════════╦════════════╦════════════╦════════════╦════════════╦════════════╗").reset());
        System.out.printf("║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║\n",
                "Location", "Time 1", "Time 2", "Time 3", "Time 4", "Time 5");
        System.out.println(Ansi.ansi().fg(MAGENTA).a("╠════════════╬════════════╬════════════╬════════════╬════════════╬════════════╣").reset());

// Display locations
        for (String[] location : locations) {
            System.out.printf(String.valueOf(Ansi.ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║\n").reset()),
                    location[0], location[1], location[2], location[3], location[4], location[5]);
        }

        System.out.println(Ansi.ansi().fg(BLUE).a("╚════════════╩════════════╩════════════╩════════════╩════════════╩════════════╝").reset());


        System.out.print("Choose Location (Enter number 1-4)-> ");
        int locationChoice = scanner.nextInt();

        System.out.println("\n📍 Location: " + locations[locationChoice - 1][0]);

        System.out.print("Choose Time Slot (Enter 1-5)-> ");
        int timeChoice = scanner.nextInt();

        System.out.println("\n🕒 Time: " + locations[locationChoice - 1][timeChoice]);
        selectedMovie = movieList.get(movieId - 1).getTitle();
        UserSelection.assignedHall = assignHall(movieList.get(movieId - 1).getId()); // ✅ Assign Hall
        UserSelection.selectedDate = dates[dateChoice - 1];
        UserSelection.selectedLocation = locations[locationChoice - 1][0];
        UserSelection.selectedTime = locations[locationChoice - 1][timeChoice];


        System.out.println("\n🎬 Movie: " + selectedMovie);
        System.out.println("\uD83C\uDFDB Hall: "+ UserSelection.assignedHall);
        System.out.println("📅 Date: " + UserSelection.selectedDate);
        System.out.println("📍 Location: " + UserSelection.selectedLocation);
        System.out.println("🕒 Time: " + UserSelection.selectedTime);

    }

    private static String assignHall(int movieId) {
        if (movieId >= 1 && movieId <= 4) {
            return "Hall A";
        } else if (movieId >= 5 && movieId <= 7) {
            return "Hall B";
        } else {
            return "Hall C";
        }

    }


    public static String formattedDuration(int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;
        return String.format("%dh %02dm", hours, minutes);
    }

    public static void viewMovies() {
    }
}