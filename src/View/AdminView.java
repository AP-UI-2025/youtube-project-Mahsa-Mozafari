package View;

import Controller.AdminController;

public class AdminView {
    private AdminController adminController;

    AdminView() {
        adminController = AdminController.getInstance();
    }

    public void handleGetAllChannels() {
        String result = adminController.getAllChannels();
        System.out.println(result);
    }

    public void handleGetAllUsers() {
        String result = adminController.getAllUsers();
        System.out.println(result);
    }

    public void handleGetAllReports() {
        String result = adminController.getAllReports();
        System.out.println(result);
    }

    public void handleGetAllContents() {
        String result = adminController.getAllContents();
        System.out.println(result);
    }

    public void handleViewPopularChannels() {
        String result = adminController.viewPopularChannels();
        System.out.println(result);
    }

    public void handleViewPopularContents() {
        String result = adminController.viewPopularContents();
        System.out.println(result);
    }

    public void handleGetAdminInfo() {
        String result = adminController.getAdminInfo();
        System.out.println(result);
    }

    public void handleConfirmReport(String[] parts) {
            int contentId = Integer.parseInt(parts[2]);
            String result = adminController.confirmReport(contentId);
            System.out.println(result);
    }

    public void handleRejectReport(String[] parts) {
            int contentId = Integer.parseInt(parts[2]);
            String result = adminController.rejectReport(contentId);
            System.out.println(result);
    }

    public void handleBanUser(String[] parts) {
            int userId = Integer.parseInt(parts[1]);
            String result = adminController.banUser(userId);
            System.out.println(result);
    }

    public void handleUnbanUser(String[] parts) {
        int userId = Integer.parseInt(parts[1]);
        String result = adminController.unbanUser(userId);
        System.out.println(result);
    }

    public void handleDeleteReportedContent(String[] parts) {
        int contentId = Integer.parseInt(parts[1]);
        String result = adminController.deleteReportedContent(contentId);
        System.out.println(result);
    }
}




