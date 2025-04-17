package Model.ContentPck;

import Model.Category;

public abstract class Video extends Content {
    private String videoSubtitle;

    public Video(ContentSpecialStatus isExclusive, String title, String description, int duration, Category category, String fileLink, String thumbnail) {
        super(isExclusive, title, description, duration, category, fileLink, thumbnail);
        this.videoSubtitle="";
    }


    public String getVideoSubtitle() {
        return videoSubtitle;
    }

    public void setVideoSubtitle(String videoSubtitle) {
        this.videoSubtitle = videoSubtitle;
    }
}
