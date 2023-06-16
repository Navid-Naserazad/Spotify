package UI;

import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControllerUserSearch_6 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private Scanner input;
    private PrintWriter output;
    private User user;
    private UserRequest userRequest;

    // Public Functions
    public void switchToSearchMusic(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMusic.fxml"));
        root = loader.load();
        ControllerSearchMusic_7 controllerSearchMusic_7 = loader.getController();
        controllerSearchMusic_7.setUser(this.user);
        controllerSearchMusic_7.setUserRequest(this.userRequest);
        controllerSearchMusic_7.setInput(this.input);
        controllerSearchMusic_7.setOutput(this.output);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSearchArtist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("searchArtist.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSearchUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("searchUser.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
