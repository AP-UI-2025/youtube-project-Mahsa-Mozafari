package Model.ContentPck;

public enum VideoResolution {
    LOW(360),MEDIUM(720),HIGH(1080);

    private final int resolution;

    VideoResolution(int resolution){
        this.resolution=resolution;
    }

    public int getResolution() {
        return resolution;
    }
}
