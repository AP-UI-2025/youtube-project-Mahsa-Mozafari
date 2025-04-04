package Controller;

import Model.AccountPck.Account;
import Model.AccountPck.User;
import Model.Category;
import Model.Database;
import Model.ContentPck.Content;


import java.util.ArrayList;
import java.util.Date;

public class ContentController {
    private static ContentController contentController;
    private Database database;
    private AuthController authController;

    public static ContentController getInstance(){
        if (contentController==null){
            contentController=new ContentController();
        }
        return contentController;
    }

    public ContentController() {
        this.database = Database.getInstance();
        this.authController=AuthController.getInstance();
    }


    public ArrayList<Content> searchContent(String searchBox) {
        ArrayList<Content> result = new ArrayList<>();
        String searchLower = searchBox.toLowerCase();

        for (Content c: database.getContents()) {
            String titleLower = c.getTitle().toLowerCase();
            if (titleLower.contains(searchLower)) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<Content> sortContentByLikes() {
        ArrayList<Content> sortedList = new ArrayList<>(database.getContents());

        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                if (sortedList.get(j).getLikes() < sortedList.get(j + 1).getLikes()) {
                    Content temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return sortedList;
    }

    public ArrayList<Content> sortContentByViews() {
        ArrayList<Content> sortedList = new ArrayList<>(database.getContents());
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                if (sortedList.get(j).getViews() < sortedList.get(j + 1).getViews()) {
                    Content temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }


    public ArrayList<Content> filterByContentType(){
        return null;
    }

    public ArrayList<Content> filterByCategory(Category category) {
        ArrayList<Content> result = new ArrayList<>();
        for (Content content : database.getContents()) {
            if (content.getCategory() == category) {
                result.add(content);
            }
        }
        return result;
    }


    public void playContent(int contentId){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
           return ;
        }
        Content content = findContentById(contentId);
        if (content != null) {
            content.setViews(content.getViews() + 1);
        }

    }

    public void likeOrDislike(int contentId){
        Account loggedInUser= authController.getLoggedInUser();
        if(!(loggedInUser instanceof User)){
            return ;
        }
        User user= (User) loggedInUser;
        Content content = findContentById(contentId);
        if (content != null) {
            if (user.getLikedContents().contains(content)) {
                content.setLikes(content.getLikes() - 1);
                user.getLikedContents().remove(content);
            } else {
                content.setLikes(content.getLikes() + 1);
               user.getLikedContents().add(content);
            }
        }
    }

    public Content findContentById(int contentId){
        for(Content content: database.getContents()){
            if(content.getContentId()==contentId){
                return content;
            }
        }
        return null;
    }

}

