package com.example.videoplayer.Panel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CreatePlaylistPanel {
    @FXML
    private void handleCancel(ActionEvent event) {

        Node source = (Node) event.getSource();
        AnchorPane popup = (AnchorPane) source.getScene().getRoot();
        AnchorPane overlay = (AnchorPane) popup.getParent();
        overlay.getChildren().clear();
        overlay.setVisible(false);
        overlay.setManaged(false);
    }
}
