package com.example.videoplayer.Panel;


import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.UserController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class LibraryPanel {
    public static Stage ctrlStage;

    @FXML
    private AnchorPane overlayPane;

    @FXML
    private ImageView userProfile;

    @FXML
    private Label userInfo;

    @FXML
    public void initialize() {
        Account loggedInUser = AuthController.getInstance().getLoggedInUser();
        if (loggedInUser instanceof User user) {
            String profileCoverLink = user.getProfileCoverLink();
            if (profileCoverLink != null && !profileCoverLink.isEmpty()) {
                Image image = new Image(profileCoverLink);
                Circle clip=new Circle(50,50,50);
                userProfile.setClip(clip);
                userProfile.setImage(image);
            }

           userInfo.setText(UserController.getInstance().getAccountInfo());
        }
    }

    @FXML
    public void showCreatePlaylistForUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/createPlaylistForUser-view.fxml"));
            AnchorPane popup = loader.load();
            CreatePlaylistForUserPanel controller=loader.getController();
            controller.setLibraryPanel(this);

            popup.setLayoutX((overlayPane.getPrefWidth() - popup.getPrefWidth()) / 2);
            popup.setLayoutY((overlayPane.getPrefHeight() - popup.getPrefHeight()) / 2);

            overlayPane.getChildren().clear();
            overlayPane.getChildren().add(popup);
            overlayPane.setVisible(true);
            overlayPane.setManaged(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeOverlay() {
        overlayPane.getChildren().clear();
        overlayPane.setVisible(false);
        overlayPane.setManaged(false);
    }

    @FXML
    void goToPremiumPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/premium-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }

    @FXML
    void goToPlaylistPanel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/playlist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }

    @FXML
    void goToCreateChannelPanel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/createChannel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }

    @FXML
    public void goToLibrary(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/library-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }

    @FXML
    public void goToChannel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/channel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }

    @FXML
    public void goToSubscription(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/subscription-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();

    }

    @FXML
    private void handleLogout() {
        boolean success = AuthController.getInstance().logout();
        if (success) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/login-view.fxml"));
                Scene scene = new Scene(loader.load());
                ctrlStage.setScene(scene);
                ctrlStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No user is currently logged in.");
            alert.show();
        }
    }

}
