package com.example.videoplayer.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

public static Stage ctrlStage;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label messageLabel;


    @FXML
    private TextField usernameText;

    @FXML
    void backToStart(ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }

    @FXML
    void loginAct(ActionEvent event) throws IOException {
        String result = AuthController.getInstance().login(usernameText.getText(), passwordText.getText());

        if (result.startsWith("success")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 680, 480);
            ctrlStage.setScene(scene);
            ctrlStage.show();
        } else if (result.equals("banned_user")) {
            messageLabel.setText("You are banned.");
        } else if (result.equals("invalid")) {
            messageLabel.setText("Invalid username or password");
        }
    }
}
