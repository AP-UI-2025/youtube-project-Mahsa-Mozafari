package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Category;
import Model.Channel;
import Model.ContentPck.*;
import Model.Database;
import Model.Playlist;

import java.util.ArrayList;
import java.util.Date;

public class ChannelController {
    private static ChannelController  channelController;
    private Database database;
    private AuthController authController;

    private ChannelController(){
        this.database=Database.getInstance();
        this.authController=AuthController.getInstance();
    }
    public static ChannelController getInstance(){
        if (channelController==null){
            channelController=new ChannelController();
        }
        return channelController;
    }


    public boolean createChannel(String channelName, String description, String channelCover) {
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;
        Channel newChannel= new Channel(channelName,description,channelCover,loggedInUser.getFullName());
        Playlist allContents=new Playlist("allContents");
        newChannel.getPlaylists().add(allContents);
        database.getChannels().add(newChannel);
        return true;
    }

    public boolean publishPodcast(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String creator) {
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return false;
                }
                Podcast newPodcast = new Podcast(code, title, description, duration, category, fileLink, thumbnail, creator);
                newPodcast.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newPodcast);
                return true;
            }
        }
        return false;

    }

    public boolean publishNormalVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String subtitle, VideoResolution resolution, VideoFormat format){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;
        for (Channel channel :database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return false;
                }
                NormalVideo newNormalVideo =new NormalVideo(code,title,description,duration,category,fileLink,thumbnail,subtitle,resolution,format);
                newNormalVideo.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newNormalVideo);
                return true;
            }
        }
        return false;
    }


    public boolean publishShortVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail,String subtitle, String musicReference) {
        if (duration >= 30) {
            return false;
        }

        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return false;
                }
                ShortVideo newShortVideo = new ShortVideo(code, title, description, duration, category, fileLink, thumbnail,subtitle,musicReference);
                newShortVideo.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newShortVideo);
                return true;
            }
        }
        return false;
    }

    public boolean publishLiveStream(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail,String subtitle, Date scheduledTime) {
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return false;
                }
                LiveStream newLiveStream = new LiveStream(code, title, description, duration, category, fileLink, thumbnail,subtitle, scheduledTime);
                newLiveStream.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newLiveStream);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Channel> searchChannel(String searchBox) {
        ArrayList<Channel> result = new ArrayList<>();
        String searchLower = searchBox.toLowerCase();

        for (Channel c: database.getChannels()) {
            String channelNameLower = c.getChannelName().toLowerCase();
            if (channelNameLower.contains(searchLower)) {
                result.add(c);
            }
        }
        return result;
    }

    public Object[] viewChannelInfo(){
        return null;
    }

    public ArrayList<Channel> showChannelContents(int channelId, String channelName){
        return null;
    }

}

