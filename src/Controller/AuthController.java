package Controller;

import Model.AccountPck.User;
import Model.Database;

public class AuthController {
    private Database database;
    private User loggedInUser;

    public AuthController() {
        this.database = Database.getInstance();
        this.loggedInUser=null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean signUp(String username, String password, String fullName, String email, String phoneNumber, String profileCoverLink) {
        return false;
    }

    public boolean login(String username, String password) {
        User user=searchForUsername(username);
        if (user!=null && user.getPassword().equals(password)){
            loggedInUser=user;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser=null;
    }

    public User searchForUsername(String username){
        for (User user : database.getUsers()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
