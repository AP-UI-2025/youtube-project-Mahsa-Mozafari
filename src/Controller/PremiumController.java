package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.PremiumPackage;
import Model.AccountPck.PremiumUser;
import Model.AccountPck.User;
import Model.Database;
import java.util.Date;


public class PremiumController {
    private static PremiumController premiumController;
    private Database database;
    private AuthController authController;
    private UserController userController;

    private PremiumController(){
        this.database=Database.getInstance();
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }

    public UserController getUserController() {
        if (userController == null) {
           userController = UserController.getInstance();
        }
        return userController;
    }

    public static PremiumController getInstance(){
        if (premiumController==null){
            premiumController=new PremiumController();
        }
        return premiumController;
    }

    public void extendSubscription(PremiumPackage packageType){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return;
        }
        User user= (User) loggedInUser;
        if (user instanceof PremiumUser) {
            PremiumUser premiumUser = (PremiumUser) user;
            Date newEndDate = new Date(premiumUser.getSubscriptionEndDate().getTime() + packageType.getDays());
            premiumUser.setSubscriptionEndDate(newEndDate);
        }

    }

    public boolean upgradePremiumPackage(PremiumPackage packageType){
        return userController.buyPremium(packageType);
    }
}
