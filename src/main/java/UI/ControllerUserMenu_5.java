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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUserMenu_5 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> likesColumn;
    @FXML
    private Label usernameLabel;
    ObservableList<Music> likesObservableList = FXCollections.observableArrayList();

    public ControllerUserMenu_5(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(this.user.getUsername());
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//        int allMusicsNumber = 0;
//        try {
//            allMusicsNumber = userRequest.numberOfAllMusics();
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 1; i <= allMusicsNumber; i++) {
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = userRequest.getRow_iMusic(i);
//            }
//            catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            String trackID = jsonObject.getString("track_id");
//            String title = jsonObject.getString("title");
//            String genre = jsonObject.getString("genre");
//            String album = jsonObject.getString("album");
//            String artists = jsonObject.getString("artist");
//            String duration = jsonObject.getString("duration");
//            musicObservableList.add(new Music(trackID, title, genre, album, artists, duration));
//        }
        tableView.setItems(likesObservableList);
    }

    // Public Functions
    public void switchToUserSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSearch.fxml"));
        root = loader.load();
        ControllerUserSearch_6 controllerUserSearch_6 = loader.getController();
        controllerUserSearch_6.setUser(this.user);
        controllerUserSearch_6.setUserRequest(this.userRequest);
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
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userEditProfile.fxml"));
        root = loader.load();
        ControllerUserEditProfile_10 controllerUserEditProfile_10 = loader.getController();
        controllerUserEditProfile_10.setUser(user);
        controllerUserEditProfile_10.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        // Log Out
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserFollowings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("followings.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerFollowings.class) {
                return new ControllerFollowings(this.user, this.userRequest);
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
    public void switchToUserFollowers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("followers.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerFollowers.class) {
                return new ControllerFollowers(this.user, this.userRequest);
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
}
