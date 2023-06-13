package UI;

import User.UserMain;
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
    Parent root;
    Stage stage;
    Scene scene;
    PrintWriter output = Main.getOutput();
    Scanner input = Main.getInput();
    public void switchToSceneUser(ActionEvent event) throws IOException {
        output.println("2");
        output.flush();
        UserMain userMain = new UserMain(input, output, new Scanner(System.in));
        root = FXMLLoader.load(getClass().getResource("sceneUser.fxml"));
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
}
