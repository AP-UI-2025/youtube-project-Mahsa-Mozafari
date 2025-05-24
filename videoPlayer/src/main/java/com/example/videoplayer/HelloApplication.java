package com.example.videoplayer;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.ChannelController;
import com.example.videoplayer.Controller.PlaylistController;
import com.example.videoplayer.Controller.UserController;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Category;
import com.example.videoplayer.Model.Channel;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/channelPlaylist-view.fxml"));
        Parent root = loader.load();

        ChannelPlaylistPanel.ctrlStage =stage;
        stage.setScene(new Scene(root));
        stage.show();


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
        UserController.getInstance().setFavoriteCategories("News");
        authController.login("Mah","Mah1385!");
        User user= (User) authController.getLoggedInUser();
        channelController.createChannel("vibe ali","vlog bahal",profilePath2);
        playlistController.createPlaylistForChannel("GameMovie");
        Playlist GameMovie =playlistController.findChannelPlaylistByName(user,"GameMovie");
        String videoPath1=new File("src/main/resources/video/Game1").getAbsolutePath();
        String coverPath1=new File("src/main/resources/Image/GamePic1.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Rush","this is Rush Movie",10, Category.Game,videoPath1,coverPath1, VideoResolution.HIGH, VideoFormat.MP4, GameMovie);
        String videoPath2=new File("src/main/resources/video/Game2").getAbsolutePath();
        String coverPath2 =new File("src/main/resources/Image/GamePic2.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Alice in Borderland","this is Alice Movie",10, Category.Game,videoPath2, coverPath2, VideoResolution.HIGH, VideoFormat.MP4, GameMovie);
        playlistController.createPlaylistForChannel("MusicLover");
        Playlist MusicLover =playlistController.findChannelPlaylistByName(user,"MusicLover");
        String videoPath3 =new File("src/main/resources/video/music3").getAbsolutePath();
        String coverPath3 =new File("src/main/resources/Image/MusicPic1.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Whiplash","this is Whiplash Movie",10, Category.Music, videoPath3, coverPath3, VideoResolution.HIGH, VideoFormat.MP4, MusicLover);
        Channel currentChannel=ChannelController.getInstance().findChannelById(1);
        ArrayList<Playlist> playlists=currentChannel.getPlaylists();

        launch(args);
    }
}