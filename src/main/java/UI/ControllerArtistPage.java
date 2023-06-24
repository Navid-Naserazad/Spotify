package UI;

import Artist.Artist;
import Artist.Music;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerArtistPage implements Initializable {
    private Artist artist;
    @FXML
    private Label nameLabel;
    @FXML
    private TableView<User> followersTableView;
    @FXML
    private TableColumn<User, String> followersColumn;
    @FXML
    private TextField followersSearch;
    ObservableList<User> followersObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Music> musicsTableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TableColumn<Music, String> trackIDColumn;
    @FXML
    private TextField musicsSearch;
    ObservableList<Music> musicsObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(this.artist.getName());

        // followers
        followersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        // database



        followersTableView.setItems(followersObservableList);
        FilteredList<User> followersFilteredList = new FilteredList<>(followersObservableList, b-> true);

        followersSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            followersFilteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String followersSearchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(followersSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> followersSortedList = new SortedList<>(followersFilteredList);
        followersSortedList.comparatorProperty().bind(followersTableView.comparatorProperty());
        followersTableView.setItems(followersSortedList);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));
        trackIDColumn.setCellValueFactory(new PropertyValueFactory<>("trackID"));

        // database

        musicsTableView.setItems(musicsObservableList);
        FilteredList<Music> musicsfilteredList = new FilteredList<>(musicsObservableList, b-> true);

        musicsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            musicsfilteredList.setPredicate(music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String musicsSearchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(musicsSearchKeyword) > -1){
                    return true;
                }
                else if (music.getAlbum().toLowerCase().indexOf(musicsSearchKeyword) > -1) {
                    return true;
                }
                else if (music.getArtists().toLowerCase().indexOf(musicsSearchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        SortedList<Music> musicsSortedList = new SortedList<>(musicsfilteredList);
        musicsSortedList.comparatorProperty().bind(musicsTableView.comparatorProperty());
        musicsTableView.setItems(musicsSortedList);
    }
}
