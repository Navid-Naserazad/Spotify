package com.example.spotify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Spotify");
        primaryStage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\com\\example\\spotify\\spotify-icon-marilyn-scott-0.png"));
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
