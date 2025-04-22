package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupPanel {
    public static Stage ctrlStage;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField profileTxt;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    void signupAct(ActionEvent event) throws IOException {
        String result = AuthController.getInstance().signup(usernameTxt.getText(), passwordTxt.getText(),fullNameTxt.getText(),emailTxt.getText(),phoneNumberTxt.getText(),profileTxt.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        switch (result) {
            case "duplicate_username":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Signup Failed");
                alert.setContentText("Username is already taken.");
                alert.show();
                break;
            case "invalid_email":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Email");
                alert.setContentText("Please enter a valid email address.");
                alert.show();
                break;
            case "invalid_phone":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Phone");
                alert.setContentText("Please enter a valid phone number.");
                alert.show();
                break;
            case "invalid_password":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Password");
                alert.setContentText("Password must be at least 8 characters.");
                alert.show();
                break;
            case "weak_password":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Weak Password");
                alert.setContentText("Password must contain uppercase, lowercase, digit, and special character.");
                alert.show();
                break;
            case "success":
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/category-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 680, 480);
                        SignupPanel.ctrlStage.setScene(scene);
                        SignupPanel.ctrlStage.show();
                break;
        }
    }
    @FXML
    void backToStart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }
}


