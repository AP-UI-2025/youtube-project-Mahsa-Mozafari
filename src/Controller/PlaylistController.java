package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;
import Model.Playlist;

public class PlaylistController {
    private static PlaylistController playlistController;
    private Database database;
    private AuthController authController;

    private PlaylistController(){
        this.database=Database.getInstance();
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }

    public static PlaylistController getInstance(){
        if (playlistController==null){
            playlistController=new PlaylistController();
        }
        return playlistController;
    }

    public boolean createPlaylistForUser(String playlistName){
        Account loggedInUser= getAuthController().getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        if (!user.canCreatePlaylist()) {
            return false;
        }

        user.getPlaylists().add(new Playlist(playlistName));
        return true;

    }

    public boolean createPlaylistForChannel(String playlistName){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                for (Playlist playlist : channel.getPlaylists()) {
                    if (playlist.getPlaylistName().equalsIgnoreCase(playlistName)) {
                        return false;
                    }
                }
                channel.getPlaylists().add(new Playlist(playlistName));
                return true;
            }
        }

        return false;
    }


    public boolean addToPlaylist(int playlistId, int contentId){

        Account loggedInUser= getAuthController().getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        Playlist playlist = findPlaylist(playlistId);
        Content content = findContent(contentId);

        if (playlist == null || content == null) return false;

        return user.addToPlaylist(playlist, content);
    }

    public Playlist findPlaylist(int playlistId) {
        Account loggedInUser= getAuthController().getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return null;
        }
        User user= (User) loggedInUser;

        for (Playlist playlist : user.getPlaylists()) {
            if (playlist.getPlaylistId() == playlistId) {
                return playlist;
            }
        }
        return null;
    }

    public Content findContent(int contentId) {
        for (Content content : database.getContents()) {
            if (content.getContentId() == contentId) {
                return content;
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

