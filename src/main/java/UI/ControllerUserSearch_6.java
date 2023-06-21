package UI;

import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUserSearch_6 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;

    // Public Functions
    public void switchToSearchMusic(ActionEvent event) throws IOException {
<<<<<<< Updated upstream
//        ControllerSearchMusic_7 controllerSearchMusic_7  = new ControllerSearchMusic_7(this.user, this.userRequest);
//        controllerSearchMusic_7.setUser(this.user);
//        controllerSearchMusic_7.setUserRequest(userRequest);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchMusic.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerSearchMusic_7.class) {
                return new ControllerSearchMusic_7(this.user, this.userRequest);
            }
            try {
                return type.getConstructor().newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        root = loader.load();
=======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMusic.fxml"));
        root = loader.load();
        ControllerSearchMusic_7 controllerSearchMusic_7 = loader.getController();
        controllerSearchMusic_7.setUser(this.user);
        controllerSearchMusic_7.setUserRequest(this.userRequest);
>>>>>>> Stashed changes
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSearchArtist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("searchArtist.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSearchUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("searchUser.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // Getter
    public UserRequest getUserRequest() {
        return userRequest;
    }

    // Setter
    public void setUser(User user) {
        this.user = user;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

}
