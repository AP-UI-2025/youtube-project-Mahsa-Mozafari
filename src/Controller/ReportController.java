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

    public static ReportController getInstance(){
        if (reportController==null){
            reportController=new ReportController();
        }
        return reportController;
    }

    public boolean createReport(int reportedContentId, String description){
        Account loggedInUser= getAuthController().getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return false;
        }
        User user= (User) loggedInUser;

        Content reportedContent=getContentController().findContentById(reportedContentId);
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
