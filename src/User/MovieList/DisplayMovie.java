package User.MovieList;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayMovie {
    private static ArrayList<Movie> movieList = new ArrayList<>();


    static {
        movieList.add(new Movie(1, "Avatar", "Sci-Fi", 134, 8.1, true, "18-02-2025"));
        movieList.add(new Movie(2, "Inception", "Action", 148, 8.8, true, "17-02-2025"));
        movieList.add(new Movie(3, "Titanic", "Romance", 95, 7.8, false, "15-02-2025"));
        movieList.add(new Movie(4, "Interstellar", "Sci-Fi", 169, 8.6, true, "15-02-2025"));
        movieList.add(new Movie(5, "Joker", "Thriller", 122, 8.4, false, "17-02-2025"));
    }

    public static void showMovies() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n---------------- Movie List ---------------------------------------");
        System.out.printf("| %-3s | %-20s | %-10s | %-8s | %-5s | %-9s | %-12s |\n",
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Release Date");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("\n");

        for (Movie movie : movieList) {
            movie.displayMovie();
        }
        System.out.println("--------------------------------------------------------------------");


        System.out.println("\n---------------- Coming Soon --------------------------------------");
        System.out.printf("| %-3s | %-20s | %-10s | %-8s | %-5s | %-9s | %-12s |\n",
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle", "Release Date");
        System.out.println("--------------------------------------------------------------------");


        ArrayList<Movie> comingSoonMovies = new ArrayList<>();
        comingSoonMovies.add(new Movie(6, "Guardians ", "Sci-Fi", 150, 9.0, true, "01-03-2025"));
        comingSoonMovies.add(new Movie(7, "The Flash", "Action", 145, 8.3, true, "10-03-2025"));
        comingSoonMovies.add(new Movie(8, "Avatar 2", "Sci-Fi", 165, 8.9, true, "15-03-2025"));
        comingSoonMovies.add(new Movie(9, "Mission: Impossible 7", "Action", 130, 8.5, false, "20-03-2025"));

        // Display Coming Soon movies
        for (Movie movie : comingSoonMovies) {
            movie.displayMovie();
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("\n");
        int idMovie=0;
        boolean validSelection = false;

        // Loop to ensure the user selects a valid movie
        if (!validSelection) {
            System.out.print("Choose a movie by ID: ");
            idMovie= scanner.nextInt();


            if (idMovie > movieList.size()) {
                System.out.println("This movie is coming soon and cannot be selected. Please choose another movie.");
            } else {
                validSelection = true;
            }
        }


        System.out.print("Choose a movie by ID: ");
        int movieId = scanner.nextInt();

        String[] dates = {"Today (18)", "Tue (19)", "Wed (20)", "Thurs (21)"};

        System.out.println("==========================================================================================");
        System.out.println("|     Today          |        Tue           |       Wed         |          Thurs         |");
        System.out.println("|       18           |         19           |        20         |           21           |");
        System.out.println("==========================================================================================");
        System.out.print("Choose Date (Enter number): ");
        int dateChoice = scanner.nextInt();

        System.out.println("\nYou have selected the movie: " + movieList.get(movieId - 1).getTitle());
        System.out.println("Date: " + dates[dateChoice - 1]);

        String[][] locations = {
                {"Mean Chey", "7:30 AM", "10:30 AM", "12:45 PM"},
                {"City Mall", "8:00 AM", "1:30 PM", "3:45 PM"},
                {"Aeon 2", "9:00 AM", "2:15 PM", "4:20 PM"},
                {"Aeon 1", "9:45 AM", "10:42 PM", "5:00 PM"}
        };

        System.out.println("========================================================================================");
        System.out.println("|     Location   |                           Time                                      |");
        System.out.println("========================================================================================");

        for (int i = 0; i < locations.length; i++) {
            System.out.printf("| %-13s | %-12s | %-12s | %-12s |\n",
                    locations[i][0], locations[i][1], locations[i][2], locations[i][3]);
        }
        System.out.println("========================================================================================");

        System.out.print("Choose Location (Enter number 1-4): ");
        int locationChoice = scanner.nextInt() - 1;

        System.out.print("Choose Time (Enter time): ");
        scanner.nextLine();
        String timeChoice = scanner.nextLine();
    }

    }

