package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Comment;
import Model.ContentPck.Content;
import Model.Database;

public class CommentController {
    private Database database;
    private static CommentController commentController;
    private AuthController authController;

    public static  CommentController getInstance(){
        if (commentController==null){
           commentController=new CommentController();
        }
        return commentController;
    }

    private CommentController(){
        this.database=Database.getInstance();
        this.authController=AuthController.getInstance();
    }

    public boolean addComment (int contentId, String text){
        Account loggedInUser = authController.getLoggedInUser();
        if (!(loggedInUser instanceof User)) {
            return false;
        }

        User user = (User) loggedInUser;

        for (Content content : database.getContents()) {
            if (content.getContentId() == contentId) {
                Comment comment = new Comment(user);
                comment.setCommentText(text);
                content.getComments().add(comment);
                return true;
            }
        }

        return false;
    }
}