package UI;

import Artist.Artist;
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
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchUser_9 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TextField search;
    private User user;
    private UserRequest userRequest;

    ObservableList<User> usersObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerSearchUser_9(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        int allUsers = 0;
        try {
            allUsers = userRequest.numberOfAllUsers();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allUsers; i++) {
            String username = null;
            try {
                username = userRequest.getRow_iUser(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            usersObservableList.add(new User(username));
        }
        tableView.setItems(usersObservableList);
        FilteredList<User> filteredList = new FilteredList<>(usersObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
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
