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

    public Object[] getAdminInfo(){
        return null;
    }

    public ArrayList<Content> viewPopularContents(){
        return null;
    }

    public ArrayList<Channel> viewPopularChannels(){
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return null;
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
        Account loggedInUser = authController.getLoggedInUser();
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
        Account loggedInUser = authController.getLoggedInUser();
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
        Account loggedInUser = authController.getLoggedInUser();
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
        Account loggedInUser = authController.getLoggedInUser();
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
        Account loggedInUser = authController.getLoggedInUser();
        if (!(loggedInUser instanceof Admin)) {
            return false;
        }

        Content contentToDelete = contentController.findContentById(reportedContentId);
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





