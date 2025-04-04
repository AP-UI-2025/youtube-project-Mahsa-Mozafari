package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.ContentPck.Content;
import Model.Database;
import Model.Report;

public class ReportController {
    private static ReportController reportController;
    private AuthController authController;
    private Database database;
    private ContentController contentController;

    private ReportController(){
        this.authController=AuthController.getInstance();
        this.database=Database.getInstance();
        this.contentController=ContentController.getInstance();

    }

    public static ReportController getInstance(){
        if (reportController==null){
            reportController=new ReportController();
        }
        return reportController;
    }

    public boolean createReport(int reportedContentId, String description){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        Content reportedContent=contentController.findContentById(reportedContentId);
        if (reportedContent==null){
            return false;
        }

        User uploader= reportedContent.getUploader();
        if(uploader==null){
            return false;
        }

        Report newReport = new Report(reportedContentId, user, description);
        newReport.setReportedUserId(uploader.getUserId());
        database.getReports().add(newReport);
        return true;

    }


}
