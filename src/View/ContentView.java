package View;


import Controller.ChannelController;
import Controller.ContentController;
import Model.Database;

public class ContentView {
    private ContentController contentController;
    ContentView(){
        this.contentController= ContentController.getInstance();
    }



}
