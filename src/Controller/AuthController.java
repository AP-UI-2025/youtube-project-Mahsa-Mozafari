package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Database;

public class AuthController {
    private static AuthController authController;
    private Database database;
    private Account loggedInUser;

    private AuthController() {
        this.database = Database.getInstance();
        this.loggedInUser=null;
    }
    public static AuthController getInstance(){
        if (authController==null){
            authController=new AuthController();
        }
        return authController;
    }

    public Account getLoggedInUser() {
        return loggedInUser;
    }

    public boolean signUp(String username, String password, String fullName, String email, String phoneNumber, String profileCoverLink) {
        return false;
    }

    public boolean login(String username, String password) {
        Account account = searchForUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            if (account instanceof User && ((User) account).isBanned()) {
                return false;
            }
            loggedInUser = account;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser=null;
    }

    public Account searchForUsername(String username){
        for (User user : database.getUsers()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
