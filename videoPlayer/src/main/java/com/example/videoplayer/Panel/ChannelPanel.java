package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.ChannelController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Channel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class ChannelPanel {

    public static Stage ctrlStage;
    @FXML
    private Button channelBtn;

    @FXML
    private ImageView channelCover;

    @FXML
    private Label channelInfo;

    @FXML
    private Button homeBtn;

    @FXML
    private Button libraryBtn;

    @FXML
    private AnchorPane overlayPane;

    @FXML
    private Button subscriptionBtn;

    @FXML
    public void initialize() {
        Account loggedInUser = AuthController.getInstance().getLoggedInUser();
        if (loggedInUser instanceof User user) {
            Channel channel = user.getChannel();

            if (channel != null) {
                String coverLink = channel.getChannelCover();
                if (coverLink != null && !coverLink.isEmpty()) {
                    Image image = new Image(coverLink);
                    Circle clip = new Circle(50, 50, 50);
                    channelCover.setClip(clip);
                    channelCover.setImage(image);
                }
                channelInfo.setText(ChannelController.getInstance().viewChannelInfo());
            } else {
                channelInfo.setText("No channel found.");
            }
        }
    }

    @FXML
    void goToChannelPlaylistPanel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/playlist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }


    @FXML
    void showSubscribers(MouseEvent event) {

    }


    @FXML
    void showCreatePlaylistForChannel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/createPlaylistForChannel.fxml"));
            AnchorPane popup = loader.load();
            CreatePlaylistForChannelPanel controller=loader.getController();
            controller.setChannelPanel(this);

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

}
