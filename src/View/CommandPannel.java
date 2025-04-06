package View;

public class CommandPannel {
    private boolean signupCompleted=false;
    private boolean isLoggedIn =false;
    private AuthView authView;
    private UserView userView;
    private ChannelView channelView;
    private PlaylistView playlistView;
    private ContentView contentView;
    private PremiumView premiumView;
    private ReportView reportView;
    private AdminView adminView;
    private CommentView commentView;
    private LibraryView libraryView;


    public CommandPannel(){
        this.userView=new UserView();
        this.authView=new AuthView();
        this.channelView=new ChannelView();
        this.playlistView=new PlaylistView();
        this.contentView=new ContentView();
        this.premiumView=new PremiumView();
        this.reportView=new ReportView();
        this.adminView=new AdminView();
        this.commentView=new CommentView();
        this.libraryView=new LibraryView();
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
            case "Search":
                contentView.handleSearch(parts);
                break;
            case "GetPremium":
                userView.handleBuyPremium(parts);
                break;
            case "IncreaseCredit":
                userView.handleIncreaseCredit(parts);
                break;
            case"ExtendSubscription":
                premiumView.handleExtendSubscription(parts);
                break;
            case"Report":
                reportView.handleCreateReport(parts);
                break;
            case"Reports":
                adminView.handleGetAllReports();
                break;
            case"Users":
                adminView.handleGetAllUsers();
                break;
            case"Channels":
                adminView.handleGetAllChannels();
                break;
            case"Contents":
                adminView.handleGetAllContents();
                break;
            case"ManageReport":
                if(parts[1].equals("C")){
                    adminView.handleConfirmReport(parts);
                } else if (parts[1].equals("R")) {
                    adminView.handleRejectReport(parts);
                }
                break;
            case"Sort":
                if(parts[1].equals("L")){
                    contentView.handleSortByLikes();
                } else if (parts[1].equals("V")) {
                    contentView.handleSortByViews();
                }
                break;
            case"Filter":
                if(parts[1].equals("P")){
                    contentView.handleFilterByPodcast();
                } else if (parts[1].equals("V")) {
                  contentView.handleFilterByVideo();
                } else if (parts[1].equals("C")) {
                    contentView.handleFilterByCategory(parts);
                }
                break;
            case"Play":
                contentView.handlePlayContent(parts);
                break;
            case "Like":
                contentView.handleLikeContent(parts);
                break;
            case "Dislike":
                contentView.handleDislikeContent(parts);
                break;
            case "EditUserInfo":
                if(parts[1].equals("N")){
                    userView.handleEditUsername(parts);
                }
                else if(parts[1].equals("P")){
                    userView.handleEditPassword(parts);
                }
                break;
            case "Edit":
                if(parts[1].equals("N")){
                    userView.handleEditChannelName(parts);
                } else if (parts[1].equals("D")) {
                    userView.handleEditChannelDescription(parts);
                }
                break;
            case"ShowChannelContent":
                userView.handleShowUserChannelContent();
                break;
            case"ViewChannel":
                channelView.handleViewChannelInfo();
                break;
            case "ViewPopularContents":
                adminView.handleViewPopularContents();
                break;
            case "ViewPopularChannels":
                adminView.handleViewPopularChannels();
                break;
            case "Subscribe":
                userView.handleSubscribe(parts);
                break;
            case"Unsubscribe":
                userView.handleUnsubscribe(parts);
                break;
            case"AccountInfo":
                userView.handleGetAccountInfo();
                break;
            case"GetSuggestions":
                contentView.handleGetSuggestions();
                break;
            case"ShowChannelSubscribers":
                userView.handleShowChannelSubscribers();
                break;
            case"AddComment":
                commentView.handleAddComment(parts);
                break;

            default:
                System.out.println("invalid command");
        }
    }
}

