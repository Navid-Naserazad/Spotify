package UI;

import Artist.Artist;
import Artist.Music;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchUser implements Initializable {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> userColumn;

    ObservableList<User> usersObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));


    }

    public void follow (ActionEvent event) throws IOException {
        ObservableList<User> users = tableView.getSelectionModel().getSelectedItems();
        User user = users.get(0);
        String username = user.getUsername();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("followedUser.fxml"));
        Parent root = loader.load();
        ControllerFollowedUser controllerFollowedUser = loader.getController();
        controllerFollowedUser.setUsername(username);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void visitPage(ActionEvent event) {

    }
    public void switchToUserSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSearch.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
