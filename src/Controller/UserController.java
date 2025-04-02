package Controller;

import Model.AccountPck.PremiumPackage;
import Model.AccountPck.PremiumUser;
import Model.AccountPck.RegularUser;
import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;


import java.util.ArrayList;
import java.util.Date;

public class UserController {
    private Database database;
    private AuthController authController;

    UserController(){
        this.database=Database.getInstance();
    }

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

    public boolean buyPremium(PremiumPackage packageType){
        User loggedInUser= authController.getLoggedInUser();
        if(loggedInUser==null){
            return false;
        }

            if (loggedInUser.getCredit() >=packageType.getPrice()) {
                loggedInUser.setCredit(loggedInUser.getCredit() - packageType.getPrice());
                if (loggedInUser instanceof RegularUser) {
                    PremiumUser premiumUser = new PremiumUser(
                            loggedInUser.getUsername(),
                            loggedInUser.getFullName(),
                            loggedInUser.getPhoneNumber(),
                            loggedInUser.getEmail(),
                            loggedInUser.getFavoriteCategories(),
                            loggedInUser.getProfileCover()
                    );
                    premiumUser.setCredit(loggedInUser.getCredit());
                    premiumUser.setSubscriptionEndDate(new Date(System.currentTimeMillis() + packageType.getDays()));
                    database.getUsers().remove(loggedInUser);
                    database.getUsers().add(premiumUser);
                } else if (loggedInUser instanceof PremiumUser) {
                    extendSubscription((PremiumUser) loggedInUser, packageType);
                }
                return true;
            }
            return false;
        }

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

