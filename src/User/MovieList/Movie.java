package User.MovieList;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int duration;
    private double rating;
    private boolean hasSubtitle;

    public Movie(int id, String title, String genre, int duration, double rating, boolean hasSubtitle) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.hasSubtitle = hasSubtitle;
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

    public void displayMovie() {
        int hours = duration / 60;
        int minutes = duration % 60;
        String durationFormatted = String.format("%dh %02dmin", hours, minutes);

        System.out.printf("| %-3d | %-20s | %-10s | %-10s | %-5.1f | %-9s |\n",
                id, title, genre, durationFormatted, rating, hasSubtitle ? "Khmer" : "English");
    }
}



