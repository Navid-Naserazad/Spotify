package UI;

import Artist.PlayList;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMyPlaylists_14 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<PlayList> tableView;
    @FXML
    private TableColumn<PlayList, String> myPlaylistsColumn;
    @FXML
    private TextField search;
    private User user;
    private UserRequest userRequest;

    ObservableList<PlayList> myPlaylistsObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerMyPlaylists_14(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources)throws RuntimeException {
        int numberOfAllPlayListOfUser = 0;
        try {
            numberOfAllPlayListOfUser = this.userRequest.numberOfAllPlayListForSpecificUser(this.user.getiD());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfAllPlayListOfUser; i++) {
            String title = null;
            try {
                title = this.userRequest.getRow_iPlayList(i, this.user.getiD());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.myPlaylistsObservableList.add(new PlayList(title));
        }

//        myPlaylistsColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//        int allPlaylists = 0;
//        try {
////            allUsers = userRequest.numberOfAllUsers();
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 1; i <= allPlaylists; i++) {
//            String title = null;
//            try {
////                title = userRequest.getRow_iUser(i);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            myPlaylistsObservableList.add(new PlayList(title));
//        }
//        tableView.setItems(myPlaylistsObservableList);
//        FilteredList<PlayList> filteredList = new FilteredList<>(myPlaylistsObservableList, b-> true);
//
//        search.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(user -> {
//
//                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
//                    return  true;
//                }
//                String searchKeyword = newValue.toLowerCase();
////                if (user.getUsername().toLowerCase().indexOf(searchKeyword ) > -1){
////                    return true;
////                }
//                else {
//                    return false;
//                }
//            });
//        });
//
//        SortedList<PlayList> sortedList = new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
//        tableView.setItems(sortedList);
    }
    public void view(ActionEvent event) throws IOException {
        ObservableList<PlayList> playLists = tableView.getSelectionModel().getSelectedItems();
        PlayList playList = playLists.get(0);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewedPlaylist.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerViewedPlaylist.class) {
                return new ControllerViewedPlaylist(this.user, this.userRequest, playList);
            }
            try {
                return type.getConstructor().newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserPlaylist(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
        root = loader.load();
        ControllerUserPlaylist_13 controllerUserPlaylist = loader.getController();
        controllerUserPlaylist.setUser(this.user);
        controllerUserPlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
