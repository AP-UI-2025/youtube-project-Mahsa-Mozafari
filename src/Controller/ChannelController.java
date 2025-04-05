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
    }
    public static ChannelController getInstance(){
        if (channelController==null){
            channelController=new ChannelController();
        }
        return channelController;
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }


    public String createChannel(String channelName, String description, String channelCover) {
        Account loggedInUser = getAuthController().getLoggedInUser();

        if (!(loggedInUser instanceof User)) {
            return "Only regular users can create channels.";
        }

        User user = (User) loggedInUser;
        Channel newChannel = new Channel(channelName, description, channelCover, loggedInUser.getFullName());
        Playlist allContents = new Playlist("allContents");
        newChannel.getPlaylists().add(allContents);
        database.getChannels().add(newChannel);

        return "Channel created successfully.";
    }

    public String publishPodcast(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String creator) {
        Account loggedInUser = getAuthController().getLoggedInUser();

        if (!(loggedInUser instanceof User)) {
            return "Only regular users can publish podcasts.";
        }

        User user = (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return "Missing 'allContents' playlist in your channel.";
                }

                Podcast newPodcast = new Podcast(code, title, description, duration, category, fileLink, thumbnail, creator);
                newPodcast.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newPodcast);
                database.getContents().add(newPodcast);
                return "Podcast published successfully.";
            }
        }

        return "You must have a channel to publish a podcast.";
    }

    public String publishNormalVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String subtitle, VideoResolution resolution, VideoFormat format){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can publish videos.";
        }

        User user = (User) loggedInUser;
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return "No valid 'allContents' playlist found.";
                }

                NormalVideo newNormalVideo = new NormalVideo(code, title, description, duration, category, fileLink, thumbnail, subtitle, resolution, format);
                newNormalVideo.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newNormalVideo);
                database.getContents().add(newNormalVideo);
                return "Normal video published successfully.";
            }
        }
        return "User does not own a channel.";
    }


    public String publishShortVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String subtitle, String musicReference) {
        if (duration >= 30) {
            return "Short video must be under 30 seconds.";
        }

        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can publish videos.";
        }

        User user = (User) loggedInUser;
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return "No valid 'allContents' playlist found.";
                }

                ShortVideo newShortVideo = new ShortVideo(code, title, description, duration, category, fileLink, thumbnail, subtitle, musicReference);
                newShortVideo.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newShortVideo);
                database.getContents().add(newShortVideo);
                return "Short video published successfully.";
            }
        }
        return "User does not own a channel.";
    }

    public String publishLiveStream(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String subtitle, Date scheduledTime) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can publish live streams.";
        }

        User user = (User) loggedInUser;
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(loggedInUser.getFullName())) {
                if (!channel.getPlaylists().get(0).getPlaylistName().equals("allContents")) {
                    return "No valid 'allContents' playlist found.";
                }

                LiveStream newLiveStream = new LiveStream(code, title, description, duration, category, fileLink, thumbnail, subtitle, scheduledTime);
                newLiveStream.setUploader(user);
                channel.getPlaylists().get(0).getContents().add(newLiveStream);
                database.getContents().add(newLiveStream);
                return "Live stream published successfully.";
            }
        }
        return "User does not own a channel.";
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

    public ArrayList<Content> showChannel(int channelId){
        Channel channel = findChannelById(channelId);

        if (channel == null) {
            return null;
        }

        return getChannelContents(channel);
    }

    public Channel findChannelById(int channelId) {
            for (Channel channel :database.getChannels()) {
                if (channel.getChannelId() == channelId) {
                    return channel;
                }
            }
            return null;
    }

    private ArrayList<Content> getChannelContents(Channel channel) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Playlist playlist : channel.getPlaylists()) {
            contents.addAll(playlist.getContents());
        }
        return contents;
    }

}

