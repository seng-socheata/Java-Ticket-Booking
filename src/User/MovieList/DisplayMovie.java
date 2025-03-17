package User.MovieList;

import User.UserSelection;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.Scanner;
import static User.UserSelection.*;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class DisplayMovie {
    public static ArrayList<Movie> movieList = new ArrayList<>();
    public static ArrayList<Movie> comingSoonMovies = new ArrayList<>();
    static {
        movieList.add(new Movie(1, "Avatar", "Sci-Fi", 134, 8.1, true,"English", "01-03-2025"));
        movieList.add(new Movie(2, "Inception", "Action", 148, 8.8, true, "Khmer","10-03-2025"));
        movieList.add(new Movie(3, "Titanic", "Romance", 95, 7.8, false,"Khmer", "28-02-2025"));
        movieList.add(new Movie(4, "Interstellar", "Sci-Fi", 169, 8.6, true,"Khmer", "11-03-2025"));
        movieList.add(new Movie(5, "Joker", "Thriller", 122, 8.4, false, "English","11-03-2025"));
        movieList.add(new Movie(6,"14th February","Horror",80,5.0,true,"Khmer","17-03-2025"));
        movieList.add(new Movie(7,"Oddity","Horror",93,5.9,false,"Khmer","14-03-2025"));
        movieList.add(new Movie(8, "Firefighter","Action",104,5.8,true,"Khmer","07-03-2025"));
        movieList.add(new Movie(9,"Her Story","Comedy",123,6.0,false,"Khmer","11-03-2025"));
        movieList.add(new Movie(10,"Undertaker","Horror",80,7.8,true,"Khmer","015-03-2025"));

        // Coming Soon Movies
        comingSoonMovies.add(new Movie(11, "Guardians", "Sci-Fi", 150, 9.0, true, "Khmer","01-04-2025"));
        comingSoonMovies.add(new Movie(12, "The Flash", "Action", 145, 8.3, true, "Khmer","08-04-2025"));
        comingSoonMovies.add(new Movie(13, "Avatar 2", "Sci-Fi", 165, 8.9, false, "English","10-04-2025"));
    }


    public static void showMovies(boolean isAdminVeiw) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(ansi().fg(GREEN).a("\n═══════════════════════════════════════════ MOVIE LIST ═════════════════════════════════════════════").reset());
        System.out.println(ansi().fg(GREEN).a("╔════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦───────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(ansi().fg(YELLOW).a("║ %-2s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Language", "Release Date");
        System.out.println(ansi().fg(BLUE).a("╠════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬───────────╬──────────────╣").reset());

        // 🎥 Display Movies
        for (Movie movie : movieList) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? "English" : "Khmer";
            System.out.printf(String.valueOf(ansi().fg(BLUE).a("║ %-2d ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                    movie.getId(), movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getLanguage(),movie.getReleaseDate());
        }
        System.out.println(ansi().fg(BLUE).a("╚════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩───────────╩──────────────╝").reset());

        System.out.println(ansi().fg(GREEN).a("\n═════════════════════════════════════════ COMING SOON MOVIES ════════════════════════════════════════").reset());
        System.out.println(ansi().fg(BLUE).a("╔═════╦──────────────────────╦───────────────╦──────────╦───────╦──────────╦───────────╦──────────────╗").reset());
        System.out.printf(String.valueOf(ansi().fg(YELLOW).a("║ %-3s ║ %-20s ║ %-13s ║ %-8s ║ %-5s ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Language", "Release Date");
        System.out.println(ansi().fg(BLUE).a("╠═════╬──────────────────────╬───────────────╬──────────╬───────╬──────────╬───────────╬──────────────╣").reset());

        // 🎥 Display Coming Soon Movies
        int comingsoonID=1;
        for (Movie movie : comingSoonMovies) {
            String durationFormatted = formattedDuration(movie.getDuration());
            String subtitleStatus = movie.hasSubtitle() ? " English" : " Khmer";
            String formattedID = String.format("%03d", comingsoonID++);
            System.out.printf(String.valueOf(ansi().fg(BLUE).a("║ %-3s ║ %-20s ║ %-13s ║ %-8s ║ %-5.1f ║ %-8s ║%-10s ║ %-12s ║\n").reset()),
                    formattedID, movie.getTitle(), movie.getGenre(), durationFormatted,
                    movie.getRating(), subtitleStatus, movie.getLanguage(),movie.getReleaseDate());
        }
        System.out.println(ansi().fg(BLUE).a("╚═════╩──────────────────────╩───────────────╩──────────╩───────╩──────────╩───────────╩──────────────╝").reset());

        if (isAdminVeiw) {
            return;
        }
          int movieId;

        while (true) {
            System.out.print("Choose a movie by ID -> ");

            if (scanner.hasNextInt()) {
                movieId = scanner.nextInt();
                if (movieId >= 1 && movieId <= movieList.size()) {
                    break;
                } else {
                    System.out.println("❌ Invalid selection! Please choose an available movie.");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.next();
            }
        }



// 📅 Choose Date
        String[] dates = {"Today (17)", "Tue (18)"};
        System.out.println(ansi().fg(GREEN).a("\n══════════════════════ AVAILABLE DATES ══════════════════════").reset());
        System.out.println(ansi().fg(BLUE).a("╔════════════╦════════════╗").reset());
        System.out.printf(String.valueOf(ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ \n").reset()),
                "Today (17)", "Tue (18)");
        System.out.println(ansi().fg(BLUE).a("╚════════════╩════════════╝").reset());

        int dateChoice;
        while (true) {
            System.out.print("Choose Date (Enter number 1-2) -> ");

            if (scanner.hasNextInt()) {
                dateChoice = scanner.nextInt();

                if (dateChoice == 1 || dateChoice == 2) {
                    break;
                } else {
                    System.out.println("❌ Invalid selection! Please enter 1 or 2.");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter a number (1 or 2).");
                scanner.next();
            }
        }


// 📍 Choose Location & Time
        String[][] locations = {
                {"Mean Chey", "7:30 AM", "10:30 AM", "12:45 PM","6:00 PM","9:30 PM"},
                {"City Mall", "8:00 AM", "1:30 PM", "3:45 PM","4:45 PM","7:30 PM"},
                {"Aeon 2", "9:00 AM", "2:15 PM", "4:20 PM","5:50 PM","10:20 PM"},
                {"Aeon 1", "9:45 AM", "10:42 PM", "5:00 PM","8:50 PM","10:00 PM "}
        };

        System.out.println(ansi().fg(GREEN).a("\n══════════════════════════ AVAILABLE LOCATIONS & TIMES ════════════════════════").reset());
        System.out.println(ansi().fg(MAGENTA).a("╔════════════╦════════════╦════════════╦════════════╦════════════╦════════════╗").reset());
        System.out.printf("║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║ %-9s  ║\n",
                "Location", "Time 1", "Time 2", "Time 3", "Time 4", "Time 5");
        System.out.println(ansi().fg(MAGENTA).a("╠════════════╬════════════╬════════════╬════════════╬════════════╬════════════╣").reset());

         // Display locations
        for (String[] location : locations) {
            System.out.printf(String.valueOf(ansi().fg(YELLOW).a("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-10s ║\n").reset()),
                    location[0], location[1], location[2], location[3], location[4], location[5]);
        }

        System.out.println(ansi().fg(BLUE).a("╚════════════╩════════════╩════════════╩════════════╩════════════╩════════════╝").reset());


        int locationChoice;
        while (true) {
            System.out.print("Choose Location (Enter number 1-4) -> ");
            if (scanner.hasNextInt()) {
                locationChoice = scanner.nextInt();
                if (locationChoice >= 1 && locationChoice <= 4) {
                    break;
                } else {
                    System.out.println("❌ Invalid selection! Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }
        System.out.println("\n📍 Location: " + locations[locationChoice - 1][0]);

        int timeChoice;
        while (true) {
            System.out.print("Choose Time Slot (Enter 1-5) -> ");

            if (scanner.hasNextInt()) {
                timeChoice = scanner.nextInt();

                if (timeChoice >= 1 && timeChoice <= 5) {
                    break;
                } else {
                    System.out.println("❌ Invalid selection! Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.next();
            }
        }

        selectedMovie = movieList.get(movieId - 1).getTitle();
        assignedHall = assignHall(movieList.get(movieId - 1).getId());
        selectedDate = dates[dateChoice - 1];
        UserSelection.selectedLocation = locations[locationChoice - 1][0];
        UserSelection.selectedTime = locations[locationChoice - 1][timeChoice];



        String[] lines = {
                "🎬 Movie: " + selectedMovie,
                "🎭 Hall: " + assignedHall,
                "📅 Date: " + selectedDate,
                "📍 Location: " + selectedLocation,
                "🕒 Time: " + selectedTime
        };

        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }
        int boxWidth = maxLength + 36;
        String boxTopBottom = "╔" + "═".repeat(boxWidth - 2) + "╗";
        String boxBottom = "╚" + "═".repeat(boxWidth - 2) + "╝";

        System.out.println(ansi().fg(Ansi.Color.GREEN).a(boxTopBottom).reset());

        for (String line : lines) {
            String paddedLine = "║ " + line + " ".repeat(boxWidth - 4 - line.length()) + " ║";
            System.out.println(ansi().fg(Ansi.Color.GREEN).a("║")
                    .fg(Ansi.Color.BLACK).a(" " + line + " ".repeat(boxWidth - 4 - line.length()) + " ")
                    .fg(Ansi.Color.GREEN).a("║")
                    .reset());
        }

        System.out.println(ansi().fg(Ansi.Color.GREEN).a(boxBottom).reset());
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