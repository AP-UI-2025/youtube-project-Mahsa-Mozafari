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
        if (parts.length < 2) {
            System.out.println("Invalid command");
        }
        double amount= Double.parseDouble(parts[1]);
        String result=userController.increaseCredit(amount);

        System.out.println(result);;
    }

    public void handleBuyPremium(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Invalid command");
        }

        PremiumPackage packageType=PremiumPackage.valueOf(parts[1].trim().toUpperCase());
            String result =userController.buyPremium(packageType);
        System.out.println(result);
    }

    public void handleGetAccountInfo(){
        String result = userController.getAccountInfo();
        System.out.println(result);
    }
}
