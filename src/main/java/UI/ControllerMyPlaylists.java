package UI;

import Artist.PlayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMyPlaylists implements Initializable {
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

    ObservableList<User> myPlaylistsObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerMyPlaylists(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    public void view(ActionEvent event) {

    }
    public void switchToUserPlaylist(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
        root = loader.load();
        ControllerUserPlaylist controllerUserPlaylist = loader.getController();
        controllerUserPlaylist.setUser(this.user);
        controllerUserPlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
