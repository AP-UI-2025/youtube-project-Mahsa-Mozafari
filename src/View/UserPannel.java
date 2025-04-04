package View;

public class UserPannel {
    private boolean signupCompleted=false;
    private AuthView authView;
    private UserView userView;

    public UserPannel(){
        this.userView=new UserView();
        this.authView=new AuthView();
    }
    public void handleCommand(String input) {
        String[] parts = input.split(" - ");
        String command = parts[0];

        if (signupCompleted && !command.equals("FavouriteCategories")){
            System.out.println("You must set your favorite categories first");
            return;
        }

        switch (command) {
            case "Signup":
                if(authView.handleSignup(parts)){
                    signupCompleted=true;

                }
                break;
            case "FavouriteCategories":
                if (parts.length < 2) {
                    System.out.println("Please provide at least one category.");
                    return;
                }
                String result = userView.handleFavoriteCategories(parts[1]);
                if (result.equals("Categories updated.")) {
                    signupCompleted = false;
                }
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

