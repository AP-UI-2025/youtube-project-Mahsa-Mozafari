package Model.ContentPck;

import Model.Category;

public class Podcast extends Content {
    private String creator;

    public Podcast(String title, int contentId, String fileLink, Category category, boolean isExclusive, String creator) {
        super(title, contentId, fileLink, category, isExclusive);
        this.creator=creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
