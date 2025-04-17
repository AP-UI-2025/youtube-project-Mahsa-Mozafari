package com.example.videoplayer.View;

import com.example.videoplayer.Controller.ContentController;
import com.example.videoplayer.Model.Category;
import com.example.videoplayer.Model.ContentPck.Content;

import java.util.ArrayList;

public class ContentView {
    private ContentController contentController;
    ContentView(){
        this.contentController= ContentController.getInstance();
    }


    public void handleSearch(String[] parts) {
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

    public void handleFilterByCategory(String[] parts) {
        String categoryStr = parts[2];
        Category category;
            category = Category.valueOf(categoryStr.toUpperCase());
        ArrayList<Content> contents = contentController.filterByCategory(category);
        if (contents.isEmpty()) {
            System.out.println("No content found in this category.");
        } else {
            System.out.println("Contents in category " + category + ":");
            for (Content c : contents) {
                System.out.println(c.getTitle());
            }
        }
    }

    public void handleGetSuggestions() {
        ArrayList<Content> suggestions = contentController.getSuggestions();

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available. You must be logged in as a user.");
            return;
        }
        System.out.println("Suggested content for you:");
        for (Content content : suggestions) {
            System.out.println(content.getTitle() +  "Category: " + content.getCategory());
        }
    }

    public void handleLikeContent(String[] parts) {
            int contentId = Integer.parseInt(parts[1]);
            Content content = contentController.findContentById(contentId);

            if (content == null) {
                System.out.println("Content not found.");
                return;
            }

            contentController.likeContent(contentId);
            System.out.println("You liked: " + content.getTitle());
    }

    public void handleDislikeContent(String[] parts) {
        int contentId = Integer.parseInt(parts[1]);
        Content content = contentController.findContentById(contentId);
        if (content == null) {
            System.out.println("Content not found.");
            return;
        }
        contentController.dislikeContent(contentId);
        System.out.println("You disliked: " + content.getTitle());
    }

    public void handlePlayContent(String[] parts) {
            int contentId = Integer.parseInt(parts[1]);
            Content content = contentController.findContentById(contentId);

            if (content == null) {
                System.out.println("Content not found.");
                return;
            }
            contentController.playContent(contentId);
            System.out.println("Now playing: " + content.getTitle());
    }


}
