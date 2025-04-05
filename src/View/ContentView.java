package View;


import Controller.ChannelController;
import Controller.ContentController;
import Model.Database;

import java.util.ArrayList;

public class ContentView {
    private ContentController contentController;
    ContentView(){
        this.contentController= ContentController.getInstance();
    }


    public void handleSearch(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Invalid command. Usage: Search - <keyword>");
            return;
        }

        String keyword = parts[1];
        ArrayList<String> results = contentController.searchInContentsAndChannels(keyword);

        if (results.isEmpty()) {
            System.out.println("No matching content or channel found.");
        } else {
            System.out.println("Search results:");
            for (String result : results) {
                System.out.println(result);
            }
        }
    }


}
