package View;

import Controller.AuthController;

public class AuthView {
    private AuthController authController;

    AuthView(){
        this.authController=AuthController.getInstance();
    }

    public boolean handleSignup(String[] parts) {
        String result = authController.signUp(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        switch (result) {
            case "success":
                System.out.println("Signup successful. Now set your favorite categories.");
                return true;
            case "duplicate_username":
                System.out.println("Username already exists.");
                break;
            case "invalid_email":
                System.out.println("Invalid email format.");
                break;
            case "invalid_phone":
                System.out.println("Invalid phone number format.");
                break;
            case "invalid_password":
                System.out.println("Password must be at least 8 characters.");
                break;
            case "weak_password":
                System.out.println("Password must contain uppercase, lowercase, digit, and symbol.");
                break;
            default:
                System.out.println("Signup failed.");
        }
        return false;
    }

    public void handleLogin(String[] parts){
        String result = authController.login(parts[1], parts[2]);

        switch (result) {
            case "success":
                System.out.println("Login successful. Welcome, " + parts[1] + "!");
                break;
            case "banned_user":
                System.out.println("You are banned. Please contact admin.");
                break;
            case "invalid":
                System.out.println("Invalid username or password.");
                break;
            default:
                System.out.println("Login failed.");
        }
    }

    public void handleLogout(){
        authController.logout();
        System.out.println("Successful Logout");
    }

}
