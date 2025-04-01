package Model.ContentPck;

import Model.Category;

public class NormalVideo extends Video {
    private VideoFormat format;
    private VideoResolution resolution;

    public NormalVideo(String title, int contentId, String fileLink, Category category, boolean isExclusive, VideoResolution resolution, VideoFormat format) {
        super(title, contentId, fileLink, category, isExclusive);
        this.format=format;
        this.resolution=resolution;
    }

    public VideoFormat getFormat() {
        return format;
    }

    public VideoResolution getResolution() {
        return resolution;
    }

    public void setFormat(VideoFormat format) {
        this.format = format;
    }

    public void setResolution(VideoResolution resolution) {
        this.resolution = resolution;
    }


}
