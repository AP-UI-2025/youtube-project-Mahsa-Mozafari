package com.example.videoplayer;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.ChannelController;
import com.example.videoplayer.Controller.PlaylistController;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Category;
import com.example.videoplayer.Model.ContentPck.ContentSpecialStatus;
import com.example.videoplayer.Model.ContentPck.VideoFormat;
import com.example.videoplayer.Model.ContentPck.VideoResolution;
import com.example.videoplayer.Model.Playlist;
import com.example.videoplayer.Panel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainPanel.ctrlStage = stage;
        LoginPanel.ctrlStage = stage;
        SignupPanel.ctrlStage=stage;
        CategoryPanel.ctrlStage=stage;
        HomePanel.ctrlStage=stage;
        LibraryPanel.ctrlStage=stage;
        CreatePlaylistForUserPanel.ctrlStage=stage;
        PlaylistPanel.ctrlStage=stage;
        CreateChannelPanel.ctrlStage=stage;
        ChannelPanel.ctrlStage=stage;
        CreatePlaylistForChannelPanel.ctrlStage=stage;
        ChannelPlaylistPanel.ctrlStage=stage;
        PublishPanel.ctrlStage=stage;
        ContentPlayerPanel.ctrlStage=stage;
        SubscriptionPanel.ctrlStage=stage;
        PremiumPanel.ctrlStage=stage;
        AdminPanel.ctrlStage=stage;


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/videoplayer/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);

        stage.setTitle("Youtube");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        AuthController authController=AuthController.getInstance();
        ChannelController channelController=ChannelController.getInstance();
        PlaylistController playlistController=PlaylistController.getInstance();
        String profilePath1 =new File("src/main/resources/Image/d1a2a5cd94d4741fc79d5350ed1b7e39.jpg").toURI().toString();
        authController.signup("Mah","Mah1385!","Mahsa","mah@gmail.com","09356669588", profilePath1);
        String profilePath2 =new File("src/main/resources/Image/afc89cbaeeb9ff9d7c4b0cd41e042f8c.jpg").toURI().toString();
        authController.signup("Asal","Asal1385!","Asalm","Asal@gmail.com","09356669599", profilePath2);

        authController.login("Mah","Mah1385!");
        User user= (User) authController.getLoggedInUser();
        channelController.createChannel("Ziba","vlog bahal",profilePath2);
        playlistController.createPlaylistForChannel("salam");
        Playlist salam=playlistController.findPlaylistByName(user,"salam");
        String videoPath1=new File("src/main/resources/video/Rec 0002.mp4").toURI().toString();
        String coverPath1=new File("src/main/resources/Image/InShot_20250425_114751640.jpg").toURI().toString();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Vibe","enjoy the vibe",10, Category.News,videoPath1,coverPath1, VideoResolution.HIGH, VideoFormat.MP4,salam);
        authController.login("Asal","Asal1385!");
        launch(args);
    }
}