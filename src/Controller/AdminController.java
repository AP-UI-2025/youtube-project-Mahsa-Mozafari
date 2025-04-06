package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.Admin;
import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Database;
import Model.Report;

import java.util.ArrayList;


public class AdminController {
    private static AdminController adminController;
    private Database database;
    private AuthController authController;
    private ContentController contentController;

    private AdminController(){
        this.database=Database.getInstance();
    }

    public AuthController getAuthController() {
        if (authController == null) {
            authController = AuthController.getInstance();
        }
        return authController;
    }

    public ContentController getContentController() {
        if (contentController == null) {
           contentController= ContentController.getInstance();
        }
        return contentController;

    }

    public static AdminController getInstance(){
        if (adminController==null){
            adminController=new AdminController();
        }
        return adminController;
    }

    public String getAdminInfo() {
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return "Admin should login";
        }
        return "Admin Info:\n" +
                "Username: " + Admin.getInstance().getUsername() + "\n" +
                "Full Name: " + Admin.getInstance().getFullName() + "\n" +
                "Phone Number: " + Admin.getInstance().getPhoneNumber() + "\n" +
                "Email: " + Admin.getInstance().getEmail() + "\n" +
                "Profile Cover: " + Admin.getInstance().getProfileCover();
    }


    public String viewPopularContents() {
        Account loggedInUser = AuthController.getInstance().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return "Access denied: Only admin can view popular contents.";
        }

        ArrayList<Content> sortedContents = contentController.sortContentByLikes();
        StringBuilder result = new StringBuilder("Most Popular Contents (by likes):\n");

        for (Content content : sortedContents) {
            result.append("Title: ").append(content.getTitle())
                    .append(" | Likes: ").append(content.getLikes())
                    .append(" | Views: ").append(content.getViews())
                    .append("\n");
        }

        return result.toString();
    }

    public String viewPopularChannels() {
        Account loggedInUser = AuthController.getInstance().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return "Access denied: Only admin can view popular channels.";
        }

        ArrayList<Channel> channels = new ArrayList<>(database.getChannels());
        for (int i = 0; i < channels.size() - 1; i++) {
            for (int j = 0; j < channels.size() - i - 1; j++) {
                if (channels.get(j).getSubscribers().size() < channels.get(j + 1).getSubscribers().size()) {
                    Channel temp = channels.get(j);
                    channels.set(j, channels.get(j + 1));
                    channels.set(j + 1, temp);
                }
            }
        }

        StringBuilder result = new StringBuilder("Most Popular Channels (by followers):\n");

        for (Channel channel : channels) {
            result.append("Channel Name: ").append(channel.getChannelName())
                    .append(" | Subscribers: ").append(channel.getSubscribers().size())
                    .append("\n");
        }

        return result.toString();
    }

    public ArrayList<User> getAllUsers() {

    }

    public ArrayList<Content> getAllContents(){
        return null;
    }

    public ArrayList<Report> getAllReports(){
        return null;
    }

    public ArrayList<Channel> getAllChannels(){
        return null;
    }

    public boolean confirmReport(int reportedContentId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }

        Report report = findReport(reportedContentId);
        if (report == null) {
            return false;
        }
        boolean contentDeleted = deleteReportedContent(reportedContentId);
        if (!contentDeleted) {
            return false;
        }
        boolean userBanned = banUser(report.getReportedUserId());

        return userBanned;

    }

    public boolean rejectReport(int reportedContentId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }
        Report report=findReport(reportedContentId);
        if(report==null){
            return false;
        }
        database.getReports().remove(report);
        return true;
    }

    public boolean unbanUser(int reportedUserId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }

        User userToUnban = findUserById(reportedUserId);
        if (userToUnban != null) {
            userToUnban.setBanned(false);
            return true;
        }
        return false;
    }

    public boolean banUser(int reportedUserId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }

        User userToBan = findUserById(reportedUserId);
        if (userToBan != null) {
            userToBan.setBanned(true);
            return true;
        }
        return false;
    }

    public boolean deleteReportedContent(int reportedContentId){
        Account loggedInUser = getAuthController().getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }

        Content contentToDelete = getContentController().findContentById(reportedContentId);
        if (contentToDelete != null) {
            database.getContents().remove(contentToDelete);
            return true;
        }
        return false;
    }
    private User findUserById(int userId) {
        for (User user : database.getUsers()) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Report findReport(int contentId){
        for (Report report: database.getReports()){
            if (report.getReportedContentId()==contentId) {
                return report;
            }
        }
        return null;
    }

}





