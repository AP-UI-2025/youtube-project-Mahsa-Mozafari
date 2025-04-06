package Controller;

import Model.AccountPck.*;
import Model.Category;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;
import Model.Playlist;


import java.util.ArrayList;
import java.util.Date;

public class UserController {
    private static UserController userController;
    private Database database;
    private AuthController authController;
    private PremiumController premiumController;
    private ChannelController channelController;

    private UserController(){
        this.database=Database.getInstance();
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }

    public ChannelController getChannelController() {
        if (channelController == null) {
            channelController = ChannelController.getInstance();
        }
        return channelController;
    }

    public PremiumController getPremiumController() {
        if (premiumController == null) {
           premiumController = PremiumController.getInstance();
        }
        return premiumController;
    }

    public static UserController getInstance(){
        if (userController==null){
            userController=new UserController();
        }
        return userController;
    }

    public String getAccountInfo(){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "User should login";
        }
        User user=(User) loggedInUser;
        return "Account Info:\n" +
                "FullName: " + user.getFullName() + "\n" +
                "Password: " + user.getPassword()+ "\n"+
                "Email: " +  user.getEmail()+"\n"+
                "Username: "+ user.getUsername()+"\n";
    }

    public boolean editUserName(String newValue){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        for (User user:database.getUsers()){
            if(user.getUsername().equalsIgnoreCase(newValue))
                return false;
        }
        loggedInUser.setUsername(newValue);
        return true;

    }

    public boolean editUserPassword(String newValue){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        for (User user:database.getUsers()){
            if(user.getPassword().equalsIgnoreCase(newValue))
                return false;
        }
        loggedInUser.setPassword(newValue);
        return true;

    }

    public boolean subscribe(int channelId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        Channel channel = getChannelController().findChannelById(channelId);
        if (channel == null) {
            return false;
        }
        if (user.getSubscriptions().contains(channel)) {
            return false;
        }

        user.getSubscriptions().add(channel);
        channel.getSubscribers().add(user);
        return true;

    }

    public boolean unsubscribe(int channelId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        Channel channel = getChannelController().findChannelById(channelId);
        if (channel == null) {
            return false;
        }
        if (!user.getSubscriptions().contains(channel)) {
            return false;
        }

        user.getSubscriptions().remove(channel);
        channel.getSubscribers().remove(user);
        return true;

    }

    public ArrayList<Content> showChannelContent(){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return null;
        }
        User user = (User) loggedInUser;
        ArrayList<Content> userContents = new ArrayList<>();
        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                userContents.addAll(getChannelContents(channel));
            }
        }

        return userContents;
    }

    private ArrayList<Content> getChannelContents(Channel channel) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Playlist playlist : channel.getPlaylists()) {
            contents.addAll(playlist.getContents());
        }
        return contents;
    }

    public int showChannelSubscribers(){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return 0;
        }

        User user = (User) loggedInUser;
        int totalSubscribers = 0;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                totalSubscribers += channel.getSubscribers().size();
            }
        }

        return totalSubscribers;
    }

    public String increaseCredit(double amount) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "No user logged in.";
        }
        User user = (User) loggedInUser;
        user.setCredit(user.getCredit() + amount);
        return "Credit increased successfully.";
    }

    public String buyPremium(PremiumPackage packageType) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "No user logged in.";
        }
        User user = (User) loggedInUser;
        double packageCost = packageType.getPrice();
        if (user.getCredit() < packageCost) {
            return "Not enough credits.";
        }

        if (user instanceof RegularUser) {
            user.setCredit(user.getCredit() - packageCost);
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
            return "Premium package purchased successfully.";
        }

        return "Failed to buy premium package.";
    }


    public boolean editChannelName(String newName){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                channel.setChannelName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean editChannelDescription(String newDescription){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        for (Channel channel : database.getChannels()) {
            if (channel.getCreator().equals(user.getFullName())) {
                channel.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }



    public String setFavoriteCategories(String input) {
        RegularUser signUpUser = getAuthController().getSignUpUser();

        if (signUpUser == null) {
            return "No user found.";
        }

        String[] parts = input.split(",");
        ArrayList<Category> selected = new ArrayList<>();

        for (String part : parts) {
            String trimmed = part.trim().toUpperCase();
            boolean isValid = false;
            for (Category c : Category.values()) {
                if (c.name().toUpperCase().equals(trimmed)) {
                    if (!selected.contains(c)) {
                        selected.add(c);
                    }
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                return "Invalid category: " + part.trim();
            }

            if (selected.size() == 4) {
                break;
            }
        }

        if (selected.isEmpty()) {
            return "Select at least one valid category.";
        }

        signUpUser.setFavoriteCategories(selected);
        return "Categories updated.";
    }

}

