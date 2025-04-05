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

    public String extendSubscription(PremiumPackage packageType) {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return "No user logged in.";
        }
        User user = (User) loggedInUser;

        if (user instanceof PremiumUser) {
            PremiumUser premiumUser = (PremiumUser) user;
            Date now = new Date();
            Date endDate = premiumUser.getSubscriptionEndDate();
            if (endDate != null && endDate.before(now) && user.getCredit() >= packageType.getPrice()) {
                user.setCredit(user.getCredit() - packageType.getPrice());
                Date newEndDate = new Date(endDate.getTime() + packageType.getDays());
                premiumUser.setSubscriptionEndDate(newEndDate);
                return "Subscription extended successfully.";
            }
        }
        return "Failed to extend subscription.";
    }

}
