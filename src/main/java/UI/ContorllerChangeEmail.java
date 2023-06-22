package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class ContorllerChangeEmail {
    // Atttributes
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    TextField currentEmail;
    @FXML
    TextField newEmail;
    @FXML
    Label message;


    public void confirm(ActionEvent event) {
        if (!currentEmail.getText().isBlank() && !newEmail.getText().isBlank()) {
            // changing Email in database
            message.setText("The Email has been successfully changed!");
        }
        else {
            message.setText("Please fill in the blanks!");
        }
    }
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userEditProfile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
