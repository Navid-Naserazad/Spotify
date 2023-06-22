package UI;

import Shared.UserRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowLyrics implements Initializable {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private Label lyrics;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
