package com.example.videoplayer.View;


import com.example.videoplayer.Controller.LibraryController;

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
