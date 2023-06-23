package UI;

import Artist.Music;
import User.User;
import Artist.PlayList;
import Shared.UserRequest;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerPlaylistPlayer_19 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private Pane pane;
    @FXML
    private Label songName;
    @FXML
    private Button playButton, pauseButton, previousButton, nextButton, resetButton, lyricsButton;
    @FXML
    private Slider volumeController;
    @FXML
    private ProgressBar songProgressBar;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;
    private int songID;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private Media media;
    private MediaPlayer mediaPlayer;

    private UserRequest userRequest;
    private User user;
    private PlayList playList;

    // Constructor
    public ControllerPlaylistPlayer_19(UserRequest userRequest, User user, PlayList playList) {
        this.userRequest = userRequest;
        this.user = user;
        this.playList = playList;
    }

    // Public Functions
    public ControllerPlaylistPlayer_19() throws IOException {
        // proprly code
        /*int numberOfMusicInPlayList = this.userRequest.numberOfAllMusicsForSpecificPlayList(this.playList.getPlayListId());
        String[] file_path = new String[numberOfMusicInPlayList];
        for (int i = 0; i < numberOfMusicInPlayList; i++) {
            file_path[i] = this.userRequest.musicAddress(this.music.getTrackID());
        }*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void PreviousButton() {
        if (songID > 0) {
            songID--;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();

        } else {
            songID = songs.size() - 1;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();
        }

    }

    public void NextButton() {
        if (songID < songs.size() - 1) {
            songID++;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();

        } else {
            songID = 0;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();
        }
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
