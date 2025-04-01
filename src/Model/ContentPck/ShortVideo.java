package Model.ContentPck;

import Model.Category;

public class ShortVideo extends Video {
    private String musicReference;

    public ShortVideo(String title, int contentId, String fileLink, Category category, boolean isExclusive) {
        super(title, contentId, fileLink, category, isExclusive);
        this.musicReference ="";
    }

    public String getMusicReference() {
        return musicReference;
    }

    public void setMusicReference(String musicReference) {
        this.musicReference = musicReference;
    }
}