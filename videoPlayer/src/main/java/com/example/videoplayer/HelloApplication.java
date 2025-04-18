package com.example.videoplayer;

import com.example.videoplayer.ViewController.LoginController;
import com.example.videoplayer.ViewController.MainController;
import com.example.videoplayer.ViewController.SignupController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainController.ctrlStage = stage;
        LoginController.ctrlStage = stage;
        SignupController.ctrlStage=stage;


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Youtube");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}