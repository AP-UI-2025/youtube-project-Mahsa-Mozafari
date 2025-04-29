package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.ContentController;
import com.example.videoplayer.Model.ContentPck.Content;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomePanel {
    public static Stage ctrlStage;
    @FXML
    private VBox mainContainer;

    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        showSuggestions();
    }

    private void showSuggestions() {
        mainContainer.getChildren().clear();
        Label title = new Label("Suggestions");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        mainContainer.getChildren().add(title);

        ArrayList<Content> suggestions = ContentController.getInstance().getSuggestions();
        for (Content content : suggestions) {
            Label label = new Label(content.getTitle() + " | " + content.getCategory());
            label.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 8;");
            mainContainer.getChildren().add(label);
        }
    }

    @FXML
    private void onSearch(ActionEvent event
    ) {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            showSuggestions();
            return;
        }

        ArrayList<String> results = ContentController.getInstance().searchInContentsAndChannels(keyword);
        mainContainer.getChildren().clear();

        Label title = new Label("Search Results");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        mainContainer.getChildren().add(title);

        if (results.isEmpty()) {
            mainContainer.getChildren().add(new Label("No results found."));
        } else {
            for (String result : results) {
                Label label = new Label(result);
                label.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 6;");
                mainContainer.getChildren().add(label);
            }
        }
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

}
