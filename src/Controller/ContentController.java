package Controller;

import Model.Category;
import Model.Database;
import Model.ContentPck.Content;


import java.util.ArrayList;
import java.util.Date;

public class ContentController {
    private Database database;

    public ContentController() {
        this.database = Database.getInstance();
    }


    public ArrayList<Content> searchContent(String searchBox) {
        return null;
    }

    public ArrayList<Content> sortContentByLikes() {
        return null;
    }

    public ArrayList<Content> sortContentByViews() {
        return null;
    }


    public ArrayList<Content> filterByContentType(){
        return null;
    }

    public ArrayList<Content> filterByCategory(Category category){
        return null;
    }

    public ArrayList<Content> filterByDate(Date start, Date end){
        return null;
    }

    public Content playContent(int contentId){
        return null;
    }

    public Content likeOrDislike(int contentId){
        return null;
    }

}

