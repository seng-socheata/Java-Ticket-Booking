package User.MovieList;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int duration;
    private double rating;
    private boolean hasSubtitle;
    private String language;
    private String releaseDate;


    public Movie(int id, String title, String genre, int duration, double rating, boolean hasSubtitle, String language, String releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.hasSubtitle = hasSubtitle;
        this.language = language;
        this.releaseDate = releaseDate;

    }
    public String getLanguage(){
        return language;
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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSubtitle(boolean hasSubtitle) {
        this.hasSubtitle = hasSubtitle;
    }

    public void displayMovie() {
        if (duration <= 0) { // Prevent errors
            System.out.println("Invalid movie duration!");
            return;
        }

        int hours = duration / 60;
        int minutes = duration % 60;
        String formattedDuration = String.format("%dh %02dm", hours, minutes);

        System.out.printf("| %-3d | %-20s | %-15s | %-8s | %-5.1f | %-9s | %-12s |\n",
                id,
                (title != null ? title : "Unknown"),
                (genre != null ? genre : "N/A"),
                formattedDuration,
                rating,
                (hasSubtitle ? "Yes" : "No"),
                (releaseDate != null ? releaseDate : "Unknown")
        );
    }
}