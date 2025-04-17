package com.example.videoplayer.View;

import com.example.videoplayer.Controller.CommentController;

public class CommentView {
    private CommentController commentController;

    CommentView(){
        this.commentController=CommentController.getInstance();
    }
    public void handleAddComment(String[] parts) {
        int contentId = Integer.parseInt(parts[1]);
        boolean success = commentController.addComment(contentId, parts[2]);
        if (success) {
            System.out.println("Comment added successfully.");
        } else {
            System.out.println("Failed to add comment. Content not found or user not logged in.");
        }
    }
}
