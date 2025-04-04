package Controller;

import Model.AccountPck.*;
import Model.Category;
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
        this.authController=AuthController.getInstance();
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

    public ArrayList<Content> getSuggestions() {
        Account account = authController.getLoggedInUser();
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

    public boolean setFavoriteCategories(String input) {

        RegularUser signUpUser= authController.getSignUpUser();

        if (signUpUser==null){
            return false;
        }


        String[] parts = input.split(",");
        ArrayList<Category> selected = new ArrayList<>();

        for (String part : parts) {
            String trimmed = part.trim().toUpperCase();

            for (Category c : Category.values()) {
                if (c.name().equals(trimmed)) {
                    boolean alreadyExists = false;
                    for (Category existing : selected) {
                        if (existing.name().equals(trimmed)) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        selected.add(c);
                    }

                    break;
                }
            }

            if (selected.size() > 4) {
                break;
            }
        }

        if (selected.size() == 4) {
            signUpUser.setFavoriteCategories(selected);
            return true;
        }

        return false;
    }

}

