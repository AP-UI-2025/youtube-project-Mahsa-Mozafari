package com.example.videoplayer.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public static Stage ctrlStage;
    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }

    @FXML
    void goToSignup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }
}
