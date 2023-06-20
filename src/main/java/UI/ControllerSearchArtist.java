package UI;

import Artist.Artist;
import Artist.Music;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchArtist implements Initializable {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<Artist> tableView;
    @FXML
    private TableColumn<Artist, String> artistColumn;

    ObservableList<Artist> artistObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


    }

    public void follow (ActionEvent event) throws IOException {
        ObservableList<Artist> artists = tableView.getSelectionModel().getSelectedItems();
        Artist artist = artists.get(0);
        String name = artist.getName();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("followedArtist.fxml"));
        Parent root = loader.load();
        ControllerFollowedArtist controllerFollowedArtist = loader.getController();
        controllerFollowedArtist.setName(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void visitPage(ActionEvent event) {

    }
    public void switchToUserSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSearch.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
