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

