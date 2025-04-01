package Controller;

import Model.Database;

public class AuthController {
    private Database database;

    public AuthController() {
        this.database = Database.getInstance();
    }

    public boolean signUp(String username, String password, String fullName, String email, String phoneNumber, String profileCoverLink) {
        return false;
    }

    public boolean login(String username, String password) {
        return false;
    }

    public void logout() {

    }

}
