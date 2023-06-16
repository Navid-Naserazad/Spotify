package UI;

import Artist.Music;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerSearchMusic_7 implements Initializable  {

    // Attributes
    private Scanner input;
    private PrintWriter output;
    private User user;
    private UserRequest userRequest;

    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> genreColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TableColumn<Music, String> durationColumn;
    @FXML
    private TextField search;

    ObservableList<Music> musicObservableList = FXCollections.observableArrayList();

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int allMusicsNumber = userRequest.numberOFAllMusics();
        for (int i = 1; i <= allMusicsNumber; i++) {
            JSONObject jsonObject = userRequest.getRow_iMusic(i);

            String title = jsonObject.getString("title");
            String genre = jsonObject.getString("genre");
            String album = jsonObject.getString("album");
            String artists = jsonObject.getString("artist");
            String duration = jsonObject.getString("duration");
            musicObservableList.add(new Music(title, genre, album, artists, duration));
        }
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

        tableView.setItems(musicObservableList);

        FilteredList<Music> filteredList = new FilteredList<>(musicObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else if (music.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getAlbum().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getArtists().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                        return false;
                }
            });
        });

        SortedList<Music> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void playButton(ActionEvent event) throws IOException {
        ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
        Music music = musics.get(0);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayer.fxml"));
        Parent root = loader.load();
        ControllerMusicPlayer controllerMusicPlayer = loader.getController();
        controllerMusicPlayer.setMusic(music);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        // music player plays the chosen music
    }
    public void downloadButton(ActionEvent event) {
        ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
        Music music = musics.get(0);
        // downloading
    }

    // Setter
    public void setInput(Scanner input) {
        this.input = input;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
