package Admin;

class movieSystem {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private double price;
    private String releaseDate;

    public movieSystem(int id, String title, String genre, String duration, double price, String releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String setTitle){
        this.title = title;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String setGenre){
        this.genre = genre;
    }
    public String getDuration(){
        return duration;
    }
    public void setDuration(String setDuration){
        this.duration = duration;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void display() {
        System.out.println("Movie ID: " + id + " | Title: " + title + " | Genre: " + genre + " | Duration: " + duration + " | Price: $" + price + " | Release Date: " + releaseDate);
    }
    
}
