package UI;

import User.User;

import Shared.UserRequest;
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

public class ControllerUserSignIn {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private Scanner input;
    private PrintWriter output;
    private UserRequest userRequest;

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label warning;

    // Public Functions
    public void switchToUserMenu(ActionEvent event, User user) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userSignIn (ActionEvent event) throws IOException {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (userRequest.usernameCheckingRequest(username)) {
                User user = userRequest.checkPasswordForLoginOperation(username, password);
                if(user == null) {
                    warning.setText("Invalid Password!");
                }
                else{
                    switchToUserMenu(event , user);
                }
            }
            else {
                warning.setText("There is no username such this in database!");
            }
        }
        else {
            warning.setText("Please Enter username and password!");
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
