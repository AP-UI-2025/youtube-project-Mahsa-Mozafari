package View;


import Controller.ChannelController;
import Controller.ContentController;
import Model.ContentPck.Content;
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

    public void handleSortByLikes() {
        ArrayList<Content> sorted = contentController.sortContentByLikes();
        if (sorted.isEmpty()) {
            System.out.println("No content found.");
            return;
        }
        System.out.println("Contents sorted by likes:");
        for (Content c : sorted) {
            System.out.println(c.getTitle() + " - Likes: " + c.getLikes());
        }
    }

    public void handleSortByViews() {
        ArrayList<Content> sorted = contentController.sortContentByViews();
        if (sorted.isEmpty()) {
            System.out.println("No content found.");
            return;
        }
        System.out.println("Contents sorted by views:");
        for (Content c : sorted) {
            System.out.println(c.getTitle() + " - Views: " + c.getViews());
        }

    }

    public void handleFilterByVideo() {
        ArrayList<Content> videos = contentController.filterByVideo();
        if (videos.isEmpty()) {
            System.out.println("No video content found.");
            return;
        }
        System.out.println("All videos:");
        for (Content v : videos) {
            System.out.println(v.getTitle());
        }
    }

    public void handleFilterByPodcast() {
        ArrayList<Content> podcasts = contentController.filterByPodcast();
        if (podcasts.isEmpty()) {
            System.out.println("No podcast content found.");
            return;
        }
        System.out.println("All podcasts:");
        for (Content p : podcasts) {
            System.out.println(p.getTitle());
        }
    }




}
