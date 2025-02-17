package User.MovieList;

import java.util.ArrayList;

public class DisplayMovie {
    private static ArrayList<Movie> movieList = new ArrayList<>();

    static {
        movieList.add(new Movie(1, "Avatar", "Sci-Fi", 134, 8.1, true));
        movieList.add(new Movie(2, "Inception", "Action", 148, 8.8, true));
        movieList.add(new Movie(3, "Titanic", "Romance", 95, 7.8, false));
        movieList.add(new Movie(4, "Interstellar", "Sci-Fi", 169, 8.6, true));
        movieList.add(new Movie(5, "Joker", "Thriller", 122, 8.4, false));
    }

    public static void showMovies() {
        System.out.println("\n---------------- Movie List ----------------------------");
        System.out.printf("| %-3s | %-20s | %-10s | %-8s | %-5s | %-9s |\n",
                "ID", "Title", "Genre", "Duration", "Rate", "Subtitle");
        System.out.println("------------------------------------------------------------");

        for (Movie movie : movieList) {
            movie.displayMovie();
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
