package Controller;

import Model.AccountPck.PremiumPackage;
import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;


import java.util.ArrayList;

public class UserController {

    public Object[] getUserInfo(){
        return null;
    }

    public User editUserName(String newValue){
        return null;
    }

    public User editUserPassword(String newValue){
        return null;
    }

    public void subscribe(int channelId){

    }

    public void unsubscribe(int channelId){

    }

    public ArrayList<Content> showChannelContent(){
        return null;
    }

    public ArrayList<User> showChannelSubscribers(){
        return null;
    }

    public double increaseCredit(double amount){
        return Double.parseDouble(null);
    }

    public boolean getPremium(PremiumPackage packageType){
        return false;
    }

    public ArrayList<Channel> showChannels(){
        return null;
    }

    public Channel editChannelName(String newName){
        return null;
    }

    public Channel editChannelDescription(String newDescription){
        return null;
    }

    public ArrayList<Content> getSuggestions(){
        return null;
    }

    public boolean getFavoriteCategories(String categoryName){
        return false;
    }

}

