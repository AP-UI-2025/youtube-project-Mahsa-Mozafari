package Model.ContentPck;

import Model.Category;

public abstract class Video extends Content {
    private String videoSubtitle;

    public Video(String title, int contentId, String fileLink, Category category, boolean isExclusive) {
        super(title, contentId, fileLink, category, isExclusive);
        this.videoSubtitle="";
    }

    public String getVideoSubtitle() {
        return videoSubtitle;
    }

    public void setVideoSubtitle(String videoSubtitle) {
        this.videoSubtitle = videoSubtitle;
    }
}
