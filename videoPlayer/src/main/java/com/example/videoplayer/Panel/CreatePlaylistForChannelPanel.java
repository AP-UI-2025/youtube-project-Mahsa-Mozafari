package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.PlaylistController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePlaylistForChannelPanel {
    public static Stage ctrlStage;

    @FXML
    private LibraryPanel libraryPanel=new LibraryPanel();
    @FXML
    private TextField playlistNameField;
    @FXML
    private AnchorPane overlayPane;

    @FXML
    void handleCreatePlaylistForUser(ActionEvent event) throws IOException {

        String playlistName = playlistNameField.getText().trim();

        if (playlistName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Playlist name cannot be empty.");
            return;
        }

        String result = PlaylistController.getInstance().createPlaylistForChannel(playlistName);

        if (result.equals("Playlist created successfully.")) {
            showAlert(Alert.AlertType.INFORMATION, "Success", result);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/channelPlaylist-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            ctrlStage.setScene(scene);
            ctrlStage.show();

        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", result);


        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void handleCancel(ActionEvent event) throws IOException {
        libraryPanel.closeOverlay();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/library-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }
}
