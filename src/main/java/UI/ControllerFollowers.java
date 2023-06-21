package UI;

import Artist.Music;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerFollowers implements Initializable {
    // Atributtes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> usernameColumn;
    @FXML
    private TextField search;

    ObservableList<User> usersObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    }
}
