package View;

import Controller.LibraryController;
import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.ContentPck.Content;

public class LibraryView {
    private LibraryController libraryController;
    LibraryView(){
        this.libraryController=LibraryController.getInstance();
    }
    public void handleShowSubscriptions(String[] parts) {
        String result = libraryController.showSubscriptions();
        System.out.println(result);
    }

    public void handleShowLikedContents(String[] parts) {
        String result = libraryController.showLikedContents();
        System.out.println(result);
    }

}
