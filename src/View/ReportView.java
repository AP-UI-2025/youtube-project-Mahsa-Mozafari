package View;

import Controller.ReportController;

public class ReportView {
    private ReportController reportController;

    ReportView(){
        this.reportController=ReportController.getInstance();
    }

    public void handleCreateReport(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command.");
            return;
        }
        int contentId;
        contentId = Integer.parseInt(parts[1]);
        String description = parts[2];
        String result = reportController.createReport(contentId, description);
        System.out.println(result);
    }

}
