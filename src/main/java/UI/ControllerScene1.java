package UI;

import Shared.UserRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControllerScene1 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private PrintWriter output;
    private Scanner input;

    // Public Functions
    public void switchToSceneUser(ActionEvent event) throws IOException {
        output.println("2");
        output.flush();
        UserRequest userRequest = new UserRequest(input, output);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneUser.fxml"));
        root = loader.load();
        ControllerSceneUser controllerSceneUser = loader.getController();
        controllerSceneUser.setInput(input);
        controllerSceneUser.setOutput(output);
        controllerSceneUser.setUserRequest(userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneArtist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene3.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Setter

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
