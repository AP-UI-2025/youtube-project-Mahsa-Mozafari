package Controller;

import Model.AccountPck.User;
import Model.ContentPck.Content;
import Model.Database;
import Model.Report;

public class ReportController {
    private static ReportController reportController;
    private AuthController authController;
    private Database database;

    private ReportController(){
        this.authController=AuthController.getInstance();
        this.database=Database.getInstance();

    }

    public static ReportController getInstance(){
        if (reportController==null){
            reportController=new ReportController();
        }
        return reportController;
    }

    public boolean createReport(int reportedContentId, String description){
        User loggedInUser = authController.getLoggedInUser();
        if (loggedInUser == null) {
            return false;
        }

        Content reportedContent=findContentById(reportedContentId);
        if (reportedContent==null){
            return false;
        }

        User uploader= reportedContent.getUploader();
        if(uploader==null){
            return false;
        }

        Report newReport = new Report(reportedContentId, loggedInUser, description);
        newReport.setReportedUserId(uploader.getUserId());
        database.getReports().add(newReport);
        return true;

    }

    private Content findContentById(int contentId){
        for(Content content: database.getContents()){
            if(content.getContentId()==contentId){
                return content;
            }
        }
        return null;
    }
}
