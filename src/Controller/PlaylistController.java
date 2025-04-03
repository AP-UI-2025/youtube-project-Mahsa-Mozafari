package Controller;

import Model.AccountPck.Account;
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
        Account loggedInUser= authController.getLoggedInUser();
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

    public Playlist createPlaylistForChannel(String playlistName){
        return null;
    }


    public boolean addToPlaylist(int playlistId, int contentId){

        Account loggedInUser= authController.getLoggedInUser();
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
        Account loggedInUser= authController.getLoggedInUser();
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

}

