package UI;

import Artist.Music;
import Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerSearchMusic implements Initializable  {
    // Attributes

    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> genreColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TableColumn<Music, String> durationColumn;
    @FXML
    private TextField search;

    ObservableList<Music> musicObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getDBConnection();
        String query = "SELECT title, genre, album, artists FROM music ASC ...";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String album = resultSet.getString("album");
                String artists = resultSet.getString("artists");
                String duration = resultSet.getString("duration");

                musicObservableList.add(new Music(title, genre, album, artists, duration));
            }
            titleColumn.setCellFactory(new PropertyValueFactory<Music, String>("title"));
            genreColumn.setCellFactory(new PropertyValueFactory<Music, String>("genre"));
            albumColumn.setCellFactory(new PropertyValueFactory<Music, String >("album"));
            artistsColumn.setCellFactory(new PropertyValueFactory<Music, String>("artists"));
            durationColumn.setCellFactory(new PropertyValueFactory<Music, String>("duration"));

            tableView.setItems(musicObservableList);

            FilteredList<Music> filteredList = new FilteredList<>(musicObservableList, b-> true);

            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(music -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return  true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if (music.getTitle().toLowerCase().indexOf(searchKeyword ) > -1){
                        return true;
                    }
                    else if (music.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (music.getAlbum().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (music.getArtists().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<Music> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void playButton(ActionEvent event) throws IOException {
        ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
        Music music = musics.get(0);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayer.fxml"));
        Parent root = loader.load();
        ControllerMusicPlayer controllerMusicPlayer = loader.getController();
        controllerMusicPlayer.setMusic(music);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        // music player plays the chosen music
    }
    public void downloadButton(ActionEvent event) {
        ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
        Music music = musics.get(0);
        // downloading
    }
}
