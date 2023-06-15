package UI;

import Artist.Music;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerMusicPlayer {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private Music music;


    public void setMusic(Music music) {
        this.music = music;
    }
}
