package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChangePasword {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    PasswordField currentPassword;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField confirmPassword;
    @FXML
    TextField message;

    public void confirm(ActionEvent event) {
        // changing password in database
        message.setText("The password has been successfully changed!");
    }
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userEditProfile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
