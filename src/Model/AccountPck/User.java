package Model.AccountPck;

import Model.Category;
import Model.Channel;
import Model.Playlist;

import java.util.ArrayList;

public abstract class User extends Account {
    private double credit;
    private ArrayList<Playlist> playlists;
    private Channel channel;
    private ArrayList<Channel> subscriptions;
    private ArrayList<Category> favoriteCategories;

    public User(String username, String fullName, String phoneNumber, String email, ArrayList<Category> favoriteCategories) {
        super(username, fullName, phoneNumber, email);
        this.credit = 0.0;
        this.favoriteCategories = favoriteCategories != null ? favoriteCategories : new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
        this.channel = null;
        playlists.add(new Playlist("Favorites"));
        playlists.add(new Playlist("Watch Later"));
    }


    public ArrayList<Category> getFavoriteCategories() {
        return favoriteCategories;
    }

    public ArrayList<Channel> getSubscriptions() {
        return subscriptions;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Channel getChannel() {
        return channel;
    }

    public double getCredit() {
        return credit;
    }

    public void setSubscriptions(ArrayList<Channel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setFavoriteCategories(ArrayList<Category> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}

