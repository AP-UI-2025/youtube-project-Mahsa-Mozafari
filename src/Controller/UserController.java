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

    public Object[] getUserInfo(){
        return null;
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

    public void increaseCredit(double amount){
        Account loggedInUser= getAuthController().getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return;
        }
        User user= (User) loggedInUser;
       if(user!=null){
           user.setCredit(user.getCredit()+amount);
       }
    }

    public boolean buyPremium(PremiumPackage packageType) {
        Account loggedInUser= getAuthController().getLoggedInUser();
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
                getPremiumController().extendSubscription(packageType);
            }
            return true;
        }
        return false;
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

    public ArrayList<Content> getSuggestions() {
        Account account = getAuthController().getLoggedInUser();
        if (!(account instanceof User)) {
            return new ArrayList<>();
        }

        User user = (User) account;
        ArrayList<Content> allContents = database.getContents();
        for (Content content : allContents) {
            content.setSuggestionPriority(0);

            if (user.getFavoriteCategories().contains(content.getCategory())) {
                content.setSuggestionPriority(content.getSuggestionPriority() + 3);
            }

            for (Content liked : user.getLikedContents()) {
                if (liked.getCategory().equals(content.getCategory())) {
                    content.setSuggestionPriority(content.getSuggestionPriority() + 2);
                    break;
                }
            }

            for (Channel channel : user.getSubscriptions()) {
                boolean contentInChannel = false;
                for (Content channelContent : database.getContents()) {
                    if (channelContent.getContentId() == content.getContentId()) {
                        contentInChannel = true;
                        break;
                    }
                }
                if (contentInChannel) {
                    content.setSuggestionPriority(content.getSuggestionPriority() + 1);
                    break;
                }
            }
        }

        for (int i = 0; i < allContents.size(); i++) {
            for (int j = i + 1; j < allContents.size(); j++) {
                if (allContents.get(i).getSuggestionPriority() < allContents.get(j).getSuggestionPriority()) {
                    Content temp = allContents.get(i);
                    allContents.set(i, allContents.get(j));
                    allContents.set(j, temp);
                }
            }
        }

        ArrayList<Content> suggestions = new ArrayList<>();
        for (Content content : allContents) {
            if (!suggestions.contains(content)) {
                suggestions.add(content);
                if (suggestions.size() == 10) break;
            }
        }

        return suggestions;
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

            for (Category c : Category.values()) {
                if (c.name().equals(trimmed) && !selected.contains(c)) {
                    selected.add(c);
                    break;
                }
            }

            if (selected.size() == 4) {
                break;
            }
        }

        if (selected.isEmpty()) {
            return "Select at least one category.";
        }

        signUpUser.setFavoriteCategories(selected);
        return "Categories updated.";
    }

}

