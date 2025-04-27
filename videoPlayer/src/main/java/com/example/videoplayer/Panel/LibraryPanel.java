package com.example.videoplayer.Panel;


import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.UserController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;

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
    public void showCreatePlaylist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/createPlaylist-view.fxml"));
            AnchorPane popup = loader.load();

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
}
