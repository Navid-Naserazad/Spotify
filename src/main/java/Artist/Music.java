package Artist;

public class Music {
    String title;
    String genre;
    String album;
    String artists;
    String duration;

    public Music(String title, String genre, String album, String artists, String duration) {
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artists = artists;
        this.duration = duration;
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
