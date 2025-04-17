package View;

import Controller.PremiumController;
import Controller.UserController;
import Model.AccountPck.PremiumPackage;

public class PremiumView {
    private PremiumController premiumController;

    PremiumView(){
        this.premiumController=PremiumController.getInstance();
    }

    public void handleExtendSubscription(String[] parts) {
        PremiumPackage packageType=PremiumPackage.valueOf(parts[1].toUpperCase());
        String result=premiumController.extendSubscription(packageType);
        System.out.println(result);
    }
}
