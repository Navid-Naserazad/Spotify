package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUserSignUp {
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

    public void switchToUserMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userSignUp (ActionEvent event) {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank() && !emailField.getText().isBlank()) {
            /*
            if (found) {
                warning.setText("This username was taken!");
            }
            else {
                switchToUserMenu(event);
                // add to DB
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
