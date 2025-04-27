package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.ContentPck.Content;
import com.example.videoplayer.Model.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlaylistPanel {
    public static Stage ctrlStage;

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
            refreshUserPlaylists();
        }

        public void refreshUserPlaylists() {
            Account loggedInUser = AuthController.getInstance().getLoggedInUser();

            if (loggedInUser instanceof User) {
                ArrayList<Playlist> playlists = ((User) loggedInUser).getPlaylists();
                loadPlaylists(playlists);
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
