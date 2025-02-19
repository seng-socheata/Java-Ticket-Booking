package User.MovieList;
import java.time.Duration;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int duration;
    private double rating;
    private boolean hasSubtitle;
    private String releaseDate;

    public Movie(int id, String title, String genre, int duration, double rating, boolean hasSubtitle, String releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.hasSubtitle = hasSubtitle;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public boolean hasSubtitle() {
        return hasSubtitle;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void displayMovie() {
        int hours = duration / 60;   // Convert minutes to hours
        int minutes = duration % 60; // Get remaining minutes
        String formattedDuration = String.format("%dh %02dm", hours, minutes);

        System.out.printf("| %-3d | %-20s | %-15s | %-8s | %-5.1f | %-9s | %-12s |\n",
                id, title, genre, formattedDuration, rating, hasSubtitle ? "Khmer" : "English", releaseDate);
    }
}



