package Controller;

import Model.AccountPck.*;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;



import java.util.ArrayList;
import java.util.Date;

public class UserController {
    private static UserController userController;
    private Database database;
    private AuthController authController;
    private PremiumController premiumController;

    private UserController(){
        this.database=Database.getInstance();
        this.premiumController=PremiumController.getInstance();
    }

    public static UserController getInstance(){
        if (userController==null){
            userController=new UserController();
        }
        return userController;
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

    public void increaseCredit(double amount){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return;
        }
        User user= (User) loggedInUser;
       if(user!=null){
           user.setCredit(user.getCredit()+amount);
       }
    }

    public boolean buyPremium(PremiumPackage packageType) {
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        double packageCost = packageType.getPrice();
        if (user.getCredit() >= packageCost) {
            user.setCredit(user.getCredit() - packageCost);
            if (user instanceof RegularUser) {
                PremiumUser premiumUser = new PremiumUser(
                        user.getUsername(),
                        user.getFullName(),
                        user.getPhoneNumber(),
                        user.getEmail(),
                        user.getFavoriteCategories(),
                        user.getProfileCover()
                );
                premiumUser.setCredit(user.getCredit());
                premiumUser.setSubscriptionEndDate(new Date(System.currentTimeMillis() + packageType.getDays()));
                database.getUsers().remove(user);
                database.getUsers().add(premiumUser);
            } else if (user instanceof PremiumUser) {
                premiumController.extendSubscription(packageType);
            }
            return true;
        }
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

