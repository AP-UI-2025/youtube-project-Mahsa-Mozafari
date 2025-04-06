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

    public void handleGetAllUsers() {
        String result = adminController.getAllUsers();
        System.out.println(result);
    }
}
