package Model.AccountPck;

import Model.Category;

import java.util.ArrayList;
import java.util.Date;

public class PremiumUser extends User {
    private Date subscriptionEndDate;

    public PremiumUser(String username, String fullName, String phoneNumber, String email, ArrayList<Category> favoriteCategories) {
        super(username, fullName, phoneNumber, email, favoriteCategories);
        this.subscriptionEndDate=new Date();
    }

    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }
}
