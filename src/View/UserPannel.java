package View;

public class UserPannel {
    private boolean signupCompleted=false;
    AuthView authView;
    public void handleCommand(String input) {
        String[] parts = input.split(" - ");
        String command = parts[0];

        if (signupCompleted && !command.equals("FavouriteCategories")){
            System.out.println("You must set your favorite categories first");
            return;;
        }

        switch (command) {
            case "Signup":
                if(authView.handleSignup(parts)){
                    signupCompleted=true;

                }
                break;
            case "FavouriteCategories":
                contentView.handleFavoriteCategories;
                break;
            case "Logout":
                authView.handleLogout();
                break;
            case "Login":
                authView.handleLogin(parts);
                break;
            default:
                System.out.println("invalid command");
        }
    }
}

