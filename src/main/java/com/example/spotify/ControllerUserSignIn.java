package com.example.spotify;

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

public class ControllerUserSignIn {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label warning;



    public void switchToUserMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userSignIn (ActionEvent event) throws IOException {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            // validating username & password
            /*
            if (validation) {
                switchToUserMenu(event);
            }
            else {
                warning.setText("Invalid username or password!");
            }
             */
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

}
