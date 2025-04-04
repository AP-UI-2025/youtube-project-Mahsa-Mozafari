package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;
import Model.Playlist;

import java.util.ArrayList;

public class LibraryController {
    private Database database;
    private static LibraryController libraryController;
    private AuthController authController;

    private LibraryController(){
        this.database=Database.getInstance();
        this.authController=AuthController.getInstance();
    }

    public static LibraryController getInstance(){
        if (libraryController==null){
            libraryController=new LibraryController();
        }
        return libraryController;
    }

    public ArrayList<Channel> showSubscriptions() {
        Account loggedInUser = authController.getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return null;
        }

        User user = (User) loggedInUser;
        return user.getSubscriptions();
    }

    public ArrayList<Playlist> showPlaylists(){
        return null;
    }

    public ArrayList<Content> showLikedPlaylistContents() {
        Account loggedInUser = authController.getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return null;
        }

        User user = (User) loggedInUser;

        Playlist likedPlaylist = null;
        for (Playlist playlist : user.getPlaylists()) {
            if (playlist.getPlaylistName().equals("Liked")) {
                likedPlaylist = playlist;
                break;
            }
        }
        if (likedPlaylist != null) {
            return likedPlaylist.getContents();
        }
        return null;
    }
}

