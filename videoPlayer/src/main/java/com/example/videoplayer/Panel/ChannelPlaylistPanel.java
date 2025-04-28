package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Channel;
import com.example.videoplayer.Model.ContentPck.Content;
import com.example.videoplayer.Model.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChannelPlaylistPanel {
    @FXML
    private Button channelBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button libraryBtn;

    @FXML
    private VBox playlistContainer;

    @FXML
    private Button subscriptionBtn;

    public void initialize() {
        refreshChannelPlaylists();
    }

    public void refreshChannelPlaylists() {
        Account loggedInUser = AuthController.getInstance().getLoggedInUser();

        if (loggedInUser instanceof User user) {
            Channel channel = user.getChannel();

            if (channel != null) {
                ArrayList<Playlist> playlists = channel.getPlaylists();
                loadPlaylists(playlists);
            } else {
                playlistContainer.getChildren().clear();
                Label noChannelLabel = new Label("You don't have a channel yet.");
                playlistContainer.getChildren().add(noChannelLabel);
            }
        } else {
            playlistContainer.getChildren().clear();
            Label noUserLabel = new Label("No user logged in or invalid account.");
            playlistContainer.getChildren().add(noUserLabel);
        }
    }
    private void loadPlaylists(ArrayList<Playlist> playlists) {
        playlistContainer.getChildren().clear();

        for (Playlist playlist : playlists) {
            VBox contentBox = new VBox(5);

            for (Content content : playlist.getContents()) {
                Label contentLabel = new Label(content.getTitle());
                contentLabel.setStyle("-fx-padding: 5 10; -fx-background-color: #f4f4f4; -fx-cursor: hand;");

                //contentLabel.setOnMouseClicked(e -> openContentPlayerPage(content));

                contentBox.getChildren().add(contentLabel);
            }

            TitledPane pane = new TitledPane(playlist.getPlaylistName(), contentBox);
            playlistContainer.getChildren().add(pane);
        }
    }

}
