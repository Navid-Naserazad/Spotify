package Artist;

public class PlayList {

    // Attributes
    private String playListId;
    private String user_id;
    private String title;

    // Constructor
    public PlayList(String playListId, String title) {
        this.playListId = playListId;
        this.title = title;
    }

    // Setter
    public String getTitle() {
        return title;
    }
    public String getPlayListId() {
        return  playListId;
    }

    // To_String
    @Override
    public String toString() {
        return "PlayList{" +
                "playListId='" + playListId + '\'' +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
