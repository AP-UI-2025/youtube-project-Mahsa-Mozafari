package View;

import Controller.UserController;
import Model.AccountPck.PremiumPackage;

public class UserView {
    private UserController userController;
    UserView(){
        this.userController=UserController.getInstance();
    }

    public String handleFavoriteCategories(String input) {
        String result = userController.setFavoriteCategories(input);
        System.out.println(result);
        return result;
    }

    public void handleIncreaseCredit(String[] parts) {
        double amount= Double.parseDouble(parts[1]);
        String result=userController.increaseCredit(amount);

        System.out.println(result);;
    }

    public void handleBuyPremium(String[] parts) {
        PremiumPackage packageType=PremiumPackage.valueOf(parts[1].trim().toUpperCase());
            String result =userController.buyPremium(packageType);
        System.out.println(result);
    }

    public void handleGetAccountInfo(){
        String result = userController.getAccountInfo();
        System.out.println(result);
    }

    public void handleEditUsername(String[] parts) {
        String result = userController.editUserName(parts[2]);
        System.out.println(result);
    }

    public void handleEditPassword(String[] parts) {
        String result = userController.editUserPassword(parts[2]);
        System.out.println(result);
    }

    public void handleSubscribe(String[] parts) {
            int channelId = Integer.parseInt(parts[1]);
            String result = userController.subscribe(channelId);
            System.out.println(result);
    }

    public void handleUnsubscribe(String[] parts) {
            int channelId = Integer.parseInt(parts[1]);
            String result = userController.unsubscribe(channelId);
            System.out.println(result);
    }

    public void handleShowChannelSubscribers() {
        String result = userController.showChannelSubscribers();
        System.out.println(result);
    }

    public void handleShowUserChannelContent() {
        String result = userController.showUserChannelContent();
        System.out.println(result);
    }

    public void handleEditChannelName(String[] parts) {
        String result = userController.editChannelName(parts[2]);
        System.out.println(result);
    }

    public void handleEditChannelDescription(String[] parts) {
        String result = userController.editChannelDescription(parts[2]);
        System.out.println(result);
    }
}
