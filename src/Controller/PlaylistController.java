package Controller;

import Model.AccountPck.User;
import Model.ContentPck.Content;
import Model.Database;
import Model.Playlist;

public class PlaylistController {
    private static PlaylistController playlistController;
    private Database database;
    private AuthController authController;

    private PlaylistController(){
        this.database=Database.getInstance();
        this.authController=AuthController.getInstance();
    }

    public static PlaylistController getInstance(){
        if (playlistController==null){
            playlistController=new PlaylistController();
        }
        return playlistController;
    }

    public boolean createPlaylistForUser(String playlistName){
        User user = authController.getLoggedInUser();
        if (user == null) return false;

        if (!user.canCreatePlaylist()) {
            return false;
        }

        user.getPlaylists().add(new Playlist(playlistName));
        return true;

    }

    public Playlist createPlaylistForChannel(String playlistName){
        return null;
    }


    public boolean addToPlaylist(int playlistId, int contentId){

        User user = authController.getLoggedInUser();
        if (user == null) return false;

        Playlist playlist = findPlaylist(playlistId);
        Content content = findContent(contentId);

        if (playlist == null || content == null) return false;

        return user.addToPlaylist(playlist, content);
    }

    public Playlist findPlaylist(int playlistId) {
        User user = authController.getLoggedInUser();
        if (user == null) return null;

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

}

