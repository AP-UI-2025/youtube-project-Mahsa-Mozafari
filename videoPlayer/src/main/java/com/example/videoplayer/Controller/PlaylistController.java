package com.example.videoplayer.Controller;


import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.AccountPck.User;
import com.example.videoplayer.Model.Channel;
import com.example.videoplayer.Model.ContentPck.Content;
import com.example.videoplayer.Model.Playlist;
import com.example.videoplayer.Model.Database;

public class PlaylistController {
    private static PlaylistController playlistController;
    private Database database;
    private AuthController authController;
    private ContentController contentController;

    private PlaylistController(){
        this.database=Database.getInstance();
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }

    public ContentController getContentController()
    {
        if (contentController == null) {
            contentController = ContentController.getInstance();
        }
        return contentController;

    }


    public static PlaylistController getInstance(){
        if (playlistController==null){
            playlistController=new PlaylistController();
        }
        return playlistController;
    }

    public String createPlaylistForUser(String playlistName){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can create playlists.";
        }

        User user = (User) loggedInUser;

        if (!user.canCreatePlaylist()) {
            return "You cannot create more playlists.";
        }

        for (Playlist p : user.getPlaylists()) {
            if (p.getPlaylistName().equalsIgnoreCase(playlistName)) {
                return "Playlist with this name already exists.";
            }
        }

        user.getPlaylists().add(new Playlist(playlistName));
        return "Playlist created successfully.";
    }

    public String createPlaylistForChannel(String playlistName){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can create channel playlists.";
        }

        User user = (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                for (Playlist playlist : channel.getPlaylists()) {
                    if (playlist.getPlaylistName().equalsIgnoreCase(playlistName)) {
                        return "Channel already has a playlist with this name.";
                    }
                }
                channel.getPlaylists().add(new Playlist(playlistName));
                return "Playlist created for channel successfully.";
            }
        }

        return "You don’t have a channel.";
    }


    public String addToPlaylist(int playlistId, int contentId) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "Only regular users can add to playlist.";
        }
        User user = (User) loggedInUser;

        Playlist playlist = findPlaylistById(playlistId);
        Content content = getContentController().findContentById(contentId);

        if (playlist == null) {
            return "Playlist not found.";
        }
        if (content == null) {
            return "Content not found.";
        }

        boolean added = user.addToPlaylist(playlist, content);
        return added ? "Content added to playlist successfully." : "Failed to add content to playlist.";
    }

    public Playlist findPlaylistById(int id) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (loggedInUser instanceof User) {
            User user = (User) loggedInUser;

            for (Playlist playlist : user.getPlaylists()) {
                if (playlist.getPlaylistId() == id) return playlist;
            }
            for (Channel channel : database.getChannels()) {
                if (channel.getCreator().equals(user.getFullName())) {
                    for (Playlist playlist : channel.getPlaylists()) {
                        if (playlist.getPlaylistId() == id) return playlist;
                    }
                }
            }
        }

        return null;
    }


    public Playlist findPlaylistByName(User user, String name) {
        for (Playlist playlist : user.getPlaylists()) {
            if (playlist.getPlaylistName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }
        return null;
    }

}

