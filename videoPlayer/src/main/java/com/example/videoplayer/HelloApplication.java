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
        ChannelSecPanel.ctrlStage=stage;
        SubscribersPanel.ctrlStage=stage;
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
        String profilePath1 =new File("src/main/resources/Image/1000049023-removebg-preview.png").toURI().toString();
        authController.signup("Mah","Mah1385!","Mahsa","mah@gmail.com","09356669588", profilePath1);
        UserController.getInstance().setFavoriteCategories("Game, Music");
        String profilePath2 =new File("src/main/resources/Image/1000049021-removebg-preview.png").toURI().toString();
        authController.signup("Asal","Asal1385!","Asalm","Asal@gmail.com","09356669599", profilePath2);
        UserController.getInstance().setFavoriteCategories("Game, History");
        authController.login("Mah","Mah1385!");
        User user= (User) authController.getLoggedInUser();
        String thumbnail1 =new File("src/main/resources/Image/1000049025-removebg-preview.png").getAbsolutePath();
        channelController.createChannel("About Movies","Talking About Movies",thumbnail1);
        playlistController.createPlaylistForChannel("Biography");
        Playlist Biography =playlistController.findChannelPlaylistByName(user,"Biography");
        String videoPath1=new File("src/main/resources/video/Theory.mp4").getAbsolutePath();
        String coverPath1=new File("src/main/resources/Image/theoryPic.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Theory Of Everything","Lovely Movie",10, Category.History,videoPath1,coverPath1, VideoResolution.HIGH, VideoFormat.MP4, Biography);
        String videoPath2 =new File("src/main/resources/video/Whiplash.mp4").getAbsolutePath();
        String coverPath2 =new File("src/main/resources/Image/whiplash.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Whiplash","this is Whiplash Movie",10, Category.Music, videoPath2, coverPath2, VideoResolution.HIGH, VideoFormat.MP4, Biography);
        playlistController.createPlaylistForChannel("Mood");
        Playlist Mood =playlistController.findChannelPlaylistByName(user,"Mood");
        String videoPath3 =new File("src/main/resources/video/Arctic.mp4").getAbsolutePath();
        String coverPath3 =new File("src/main/resources/Image/arcric.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Arctic Monkeys","Arctic Monkeys Concert",10, Category.Music, videoPath3, coverPath3, VideoResolution.HIGH, VideoFormat.MP4, Mood);

        Channel currentChannel1=ChannelController.getInstance().findChannelById(1);
        ArrayList<Playlist> playlists1=currentChannel1.getPlaylists();
        authController.logout();

        authController.login("Asal","Asal1385!");
        User user2 = (User) authController.getLoggedInUser();
        String thumbnail2 =new File("src/main/resources/Image/1000049015-removebg-preview.png").getAbsolutePath();
        channelController.createChannel("Vlog","Watch My Daily",thumbnail2);
        playlistController.createPlaylistForChannel("Nature");
        Playlist Nature =playlistController.findChannelPlaylistByName(user2,"Nature");
        String videoPath5 =new File("src/main/resources/video/nature.mp4").getAbsolutePath();
        String coverPath5 =new File("src/main/resources/Image/nature2.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"About Nature","Beauty of nature",10, Category.Society, videoPath5, coverPath5, VideoResolution.HIGH, VideoFormat.MP4, Nature);
        playlistController.createPlaylistForChannel("Piano");
        Playlist Piano =playlistController.findChannelPlaylistByName(user2,"Piano");
        String podcastPath1 =new File("src/main/resources/Podcast/piano.mp3").getAbsolutePath();
        String coverPath6 =new File("src/main/resources/Image/piano.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Idea 15","Piano",10, Category.Podcast, podcastPath1, coverPath6, VideoResolution.HIGH, VideoFormat.MP4, Piano);
        Channel currentChannel2 =ChannelController.getInstance().findChannelById(2);
        ArrayList<Playlist> playlists2= currentChannel2.getPlaylists();

        String profilePath3 =new File("src/main/resources/Image/1000049019-removebg-preview.png").toURI().toString();
        authController.signup("Ali","Ali1385!","Ali","ali@gmail.com","09356669588", profilePath3);
        UserController.getInstance().setFavoriteCategories("Podcast, Music");
        authController.login("Ali","Ali1385!");
        User user3 = (User) authController.getLoggedInUser();
        String thumbnail3 =new File("src/main/resources/Image/1000049018-removebg-preview.png").getAbsolutePath();
        channelController.createChannel("Comfort Zone","Take deep breath", thumbnail3);
        playlistController.createPlaylistForChannel("Aesthetic");
        Playlist Aesthetic =playlistController.findChannelPlaylistByName(user3,"Aesthetic");
        String videoPath7=new File("src/main/resources/video/Kdrama.mp4").getAbsolutePath();
        String coverPath7 =new File("src/main/resources/Image/kdrama.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"When Life Gives You Tangerines","Lovely Movie",10, Category.Society,videoPath7, coverPath7, VideoResolution.HIGH, VideoFormat.MP4, Aesthetic);
        String videoPath8 =new File("src/main/resources/video/LittleWoman.mp4").getAbsolutePath();
        String coverPath8 =new File("src/main/resources/Image/littleW.jpg").getAbsolutePath();
        channelController.publishNormalVideo(ContentSpecialStatus.NOT_SPECIAL,"Little Woman","Lovely Movie",10, Category.History, videoPath8, coverPath8, VideoResolution.HIGH, VideoFormat.MP4, Aesthetic);
        Channel currentChannel3 =ChannelController.getInstance().findChannelById(2);
        ArrayList<Playlist> playlists3= currentChannel3.getPlaylists();
        launch(args);
    }
}