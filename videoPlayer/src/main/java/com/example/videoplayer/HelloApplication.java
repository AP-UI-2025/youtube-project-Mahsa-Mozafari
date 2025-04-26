package com.example.videoplayer;

import com.example.videoplayer.Panel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainPanel.ctrlStage = stage;
        LoginPanel.ctrlStage = stage;
        SignupPanel.ctrlStage=stage;
        CategoryPanel.ctrlStage=stage;
        HomePanel.ctrlStage=stage;


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Youtube");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}