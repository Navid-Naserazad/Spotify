package UI;

import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUserMenu {
    Parent root;
    Stage stage;
    Scene scene;
    public void switchToUserSearch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userSearch.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserPlaylist(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("userPlaylist.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userEditProfile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserFollowings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userFollowings.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserFollowers(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userFollowers.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserMyProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMyProfile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
