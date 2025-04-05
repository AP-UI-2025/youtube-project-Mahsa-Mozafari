package View;

public class CommandPannel {
    private boolean signupCompleted=false;
    private boolean isLoggedIn =false;
    private AuthView authView;
    private UserView userView;
    private ChannelView channelView;
    private PlaylistView playlistView;

    public CommandPannel(){
        this.userView=new UserView();
        this.authView=new AuthView();
        this.channelView=new ChannelView();
        this.playlistView=new PlaylistView();
    }
    public void handleCommand(String input) {
        String[] parts = input.split(" - ");
        String command = parts[0];

        if (signupCompleted && !command.equals("FavouriteCategories")){
            System.out.println("You must set your favorite categories first");
            return;
        }

        if (!isLoggedIn && !command.equals("Signup") && !command.equals("FavouriteCategories")&&!command.equals("Login")) {
            System.out.println("You must log in first.");
            return;
        }

        switch (command) {
            case "Signup":
                if (authView.handleSignup(parts)) {
                    signupCompleted = true;

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
                isLoggedIn =false;

                break;
            case "Login":
                if (authView.handleLogin(parts)){
                    isLoggedIn = true;
                    System.out.println("Login successfully");
        }
                break;

            case "CreateChannel":
                channelView.handleCreateChannel(parts);
                break;
            case "CreatePlaylist":
                if(parts[1].equals("U")){
                    playlistView.handleCreatePlaylistForUser(parts);
                } else if (parts[1].equals("C")) {
                    playlistView.handleCreatePlaylistForChannel(parts);
                }
                break;
            case "AddToPlaylist":
                playlistView.handleAddToPlaylist(parts);
                break;
            case "Publish":
                if(parts[1].equals("NV")){
                    channelView.handlePublishNormalVideo(parts);
                } else if (parts[1].equals("SV")) {
                    channelView.handlePublishShortVideo(parts);
                } else if (parts[1].equals("P")) {
                    channelView.handlePublishPodcast(parts);
                } else if (parts[1].equals("LS")) {
                    channelView.handlePublishLiveStream(parts);
                }
                break;
            default:
                System.out.println("invalid command");
        }
    }
}

