package Artist;

public class Music {
    private String trackID;
    private String title;
    private String genre;
    private String album;
    private String artists;
    private String duration;

    public Music(String trackID, String title, String genre, String album, String artists, String duration) {
        this.trackID = trackID;
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artists = artists;
        this.duration = duration;
    }

    public String getTrackID() {
        return trackID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtists() {
        return artists;
    }

    public String getDuration() {
        return duration;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", artists='" + artists + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
