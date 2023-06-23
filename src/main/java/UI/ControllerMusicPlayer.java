package UI;

import Artist.Music;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.AclEntryType;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMusicPlayer implements Initializable {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private Music music;
    @FXML
    private Pane pane;
    @FXML
    private Label songName;
    @FXML
    private Button playButton, pauseButton, lyricsButton, resetButton;
    @FXML
    private Slider volumeController;
    @FXML
    private ProgressBar songProgressBar;

    private File directory;
//    private File[] files;
//
//    private ArrayList<File> songs;
//    private int songID;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    private Media media;
    private MediaPlayer mediaPlayer;


    public ControllerMusicPlayer(Music music) {
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songName.setText(getMusic().getTitle());
        // getting file (music) directory from database

        // directory = new File();
        media = new Media(directory.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        volumeController.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeController.getValue() * 0.01);
            }
        });
        songProgressBar.setStyle("-fx-accent: #18ac18;");
    }

    public void PlayButton() {
        BeginTimer();
        mediaPlayer.setVolume(volumeController.getValue() * 0.01);
        mediaPlayer.play();

    }

    public void PauseButton() {
        CancelTimer();
        mediaPlayer.pause();

    }

    public void ResetButton() {
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }
    public void BeginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run(){
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);
                if(current/end == 1){
                    CancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);
    }

    public void CancelTimer() {
        running = false;
        timer.cancel();

    }
    public void showLyrics(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Spotify");
        stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showLyrics.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerShowLyrics.class) {
                return new ControllerShowLyrics();
            }
            try {
                return type.getConstructor().newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
