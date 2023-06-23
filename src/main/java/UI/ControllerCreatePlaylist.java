package UI;

import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreatePlaylist {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private TextField playlistTitle;
    public void setUser(User user) {
        this.user = user;
    }
    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
    public void nextButton(ActionEvent event) throws IOException {
        if (!playlistTitle.getText().isBlank()) {
            String title = playlistTitle.getText();
            // add the new playlist to the database
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addMusicToCreatedPlaylist.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerAddMusicCreatedPlaylist.class) {
                    return new ControllerAddMusicCreatedPlaylist(this.user, this.userRequest, title);
                }
                try {
                    return type.getConstructor().newInstance();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void switchToUserPlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
        root = loader.load();
        ControllerUserPlaylist controllerUserPlaylist = loader.getController();
        controllerUserPlaylist.setUser(this.user);
        controllerUserPlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
