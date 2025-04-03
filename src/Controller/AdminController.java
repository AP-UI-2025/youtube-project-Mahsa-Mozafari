package Controller;

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
    private ReportController reportController;

    private AdminController(){
        this.database=Database.getInstance();
        this.authController=AuthController.getInstance();
        this.reportController=ReportController.getInstance();

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

    public void confirmReport(int reportedContentId){

    }

    public void rejectReport(int reportedContentId){

    }

    public boolean unbanUser(int reportedUserId){
        return false;
    }

    public boolean banUser(int reportedUserId){
        return false;
    }

    public boolean deleteReportedContent(int reportedContentId){
        return false;
    }

}

