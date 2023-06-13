package UI;

import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControllerUserSignUp {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField emailField;
    @FXML
    Label warning;
    private Scanner input;
    private PrintWriter output;
    private UserRequest userRequest;

    // Public Functions
    public void switchToUserMenu(ActionEvent event, User user) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userSignUp (ActionEvent event) throws IOException {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank() && !emailField.getText().isBlank()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String emailAddress = emailField.getText();
            if (!userRequest.usernameCheckingRequest(username)) {
                User user = new User(username, password, emailAddress);
                userRequest.addClientToDB(user);
                switchToUserMenu(event, user);
            }
            else {
                warning.setText("This username is taken!");
            }
        }
        else {
            warning.setText("Please fill all blankets!");
        }

    }
    public void switchToSceneUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sceneUser.fxml"));
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

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
