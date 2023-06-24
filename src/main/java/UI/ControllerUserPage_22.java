package UI;

import Artist.Artist;
import Artist.PlayList;
import Artist.Music;
import Shared.UserRequest;
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

public class ControllerUserPage_22 implements Initializable {

    // Attributes
    private User selectedUser;
    private UserRequest userRequest;
    @FXML
    private Label usernameLabel;
    @FXML
    private TableView<User> followersTableView;
    @FXML
    private TableColumn<User, String> followersColumn;
    @FXML
    private TextField followersSearch;
    ObservableList<User> followersObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> userFollowingsTableView;
    @FXML
    private TableColumn<User, String> userFollowingsColumn;
    @FXML
    private TextField userFollowingsSearch;
    ObservableList<User> userFollowingsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Artist> artistFollowingsTableView;
    @FXML
    private TableColumn<Artist, String> artistFollowingsColumn;
    @FXML
    private TextField artistFollowingsSearch;
    ObservableList<Artist> artistFollowingsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<PlayList> playlistsTableView;
    @FXML
    private TableColumn<PlayList, String> playlistsColumn;
    @FXML
    private TextField playlistsSearch;
    ObservableList<PlayList> playlistsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Music> likesTableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TextField likesSearch;
    ObservableList<Music> likesObservableList = FXCollections.observableArrayList();

    // Constructor
    public ControllerUserPage_22(User selectedUser, UserRequest userRequest) {
        this.selectedUser = selectedUser;
        this.userRequest = userRequest;
    }

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(this.selectedUser.getUsername());

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

        // user followings
        userFollowingsColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        // database



        userFollowingsTableView.setItems(userFollowingsObservableList);
        FilteredList<User> userFollowingsFilteredList = new FilteredList<>(userFollowingsObservableList, b-> true);

        userFollowingsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            userFollowingsFilteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String userFollowingsSearchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(userFollowingsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> userFollowingsSortedList = new SortedList<>(userFollowingsFilteredList);
        userFollowingsSortedList.comparatorProperty().bind(userFollowingsTableView.comparatorProperty());
        userFollowingsTableView.setItems(userFollowingsSortedList);

        // artist followings
        artistFollowingsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // database



        artistFollowingsTableView.setItems(artistFollowingsObservableList);
        FilteredList<Artist> artistFollowingsFilteredList = new FilteredList<>(artistFollowingsObservableList, b-> true);

        artistFollowingsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            artistFollowingsFilteredList.setPredicate(artist -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String artistFollowingsSearchKeyword = newValue.toLowerCase();
                if (artist.getName().toLowerCase().indexOf(artistFollowingsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Artist> artistFollowingsSortedList = new SortedList<>(artistFollowingsFilteredList);
        artistFollowingsSortedList.comparatorProperty().bind(artistFollowingsTableView.comparatorProperty());
        artistFollowingsTableView.setItems(artistFollowingsSortedList);

        // playlists
        playlistsColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // database



        playlistsTableView.setItems(playlistsObservableList);
        FilteredList<PlayList> playlistsFilteredList = new FilteredList<>(playlistsObservableList, b-> true);

        playlistsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            playlistsFilteredList.setPredicate( playList -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String playlistsSearchKeyword = newValue.toLowerCase();
                if (playList.getTitle().toLowerCase().indexOf(playlistsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<PlayList> playListsSortedList = new SortedList<>(playlistsFilteredList);
        playListsSortedList.comparatorProperty().bind(playlistsTableView.comparatorProperty());
        playlistsTableView.setItems(playListsSortedList);

        // likes
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

        // database



        likesTableView.setItems(likesObservableList);
        FilteredList<Music> likesFilteredList = new FilteredList<>(likesObservableList, b-> true);

        likesSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            likesFilteredList.setPredicate( music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String likesSearchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(likesSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Music> likesSortedList = new SortedList<>(likesFilteredList);
        likesSortedList.comparatorProperty().bind(likesTableView.comparatorProperty());
        likesTableView.setItems(likesSortedList);
    }
}
