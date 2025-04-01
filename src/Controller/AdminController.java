package Controller;

import Model.AccountPck.User;
import Model.Channel;
import Model.ContentPck.Content;
import Model.Report;

import java.util.ArrayList;

public class AdminController {

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

    public void manageReports(int reportedContentId,String action){

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

