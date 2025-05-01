package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.ContentController;
import com.example.videoplayer.Model.Channel;
import com.example.videoplayer.Model.ContentPck.Content;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
            HBox itemBox = new HBox(10);
            itemBox.setAlignment(Pos.CENTER_LEFT);

            ImageView thumbnail = new ImageView(new Image("file:" + content.getThumbnail()));
            thumbnail.setFitWidth(100);
            thumbnail.setFitHeight(60);
            thumbnail.setPreserveRatio(true);

            Label label = new Label(content.getTitle() + " | " + content.getCategory());
            label.setStyle("-fx-font-size: 14px;");

            thumbnail.setOnMouseClicked(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/contentPlayer-view.fxml"));
                    Scene scene = new Scene(loader.load(),800,900);
                    ContentPlayerPanel controller = loader.getController();
                    controller.setContent(content);
                    ctrlStage.setScene(scene);
                    ctrlStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            itemBox.getChildren().addAll(thumbnail, label);
            mainContainer.getChildren().add(itemBox);
        }
    }


    @FXML
    private void onSearch(ActionEvent event) {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            showSuggestions();
            return;
        }

        mainContainer.getChildren().clear();
        Label title = new Label("Search Results");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        mainContainer.getChildren().add(title);

        Map<String, ArrayList<?>> results = ContentController.getInstance().searchInContentsAndChannels(keyword);
        ArrayList<Content> contents = (ArrayList<Content>) results.get("contents");
        ArrayList<Channel> channels = (ArrayList<Channel>) results.get("channels");


        if (contents.isEmpty() && channels.isEmpty()) {
            mainContainer.getChildren().add(new Label("No results found."));
            return;
        }

        for (Content content : contents) {
            mainContainer.getChildren().add(createContentBox(content));
        }

        for (Channel channel : channels) {
            mainContainer.getChildren().add(createChannelBox(channel));
        }
    }
    private HBox createContentBox(Content content) {
        ImageView imageView = new ImageView(new Image("file:" + content.getThumbnail()));
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        Label label = new Label(content.getTitle());
        label.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        HBox box = new HBox(10, imageView, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

       imageView.setOnMouseClicked(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/contentPlayer-view.fxml"));
                Scene scene = new Scene(loader.load(),800,900);
                ContentPlayerPanel controller = loader.getController();
                controller.setContent(content);
                ctrlStage.setScene(scene);
                ctrlStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return box;
    }

    private HBox createChannelBox(Channel channel) {
        ImageView imageView = new ImageView(new Image("file:" + channel.getChannelCover()));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label label = new Label(channel.getChannelName());
        label.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        HBox box = new HBox(10, imageView, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-padding: 10; -fx-background-color: #e0e0e0;");

        /*imageView.setOnMouseClicked(e -> {
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/contentPlayer-view.fxml"));
            Scene scene = new Scene(loader.load(),800,900);
            ChannelPanel controller = loader.getController();
            controller.;
            ctrlStage.setScene(scene);
            ctrlStage.show();
        } catch (IOException ex){
            ex.printStackTrace();

        }
        });
        */

        return box;
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

}
