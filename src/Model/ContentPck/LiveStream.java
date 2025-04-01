package Model.ContentPck;

import Model.Category;

import java.util.Date;

public class LiveStream extends Video {
    private int viewers;
    private Date scheduledTime;

    public LiveStream(String title, int contentId, String fileLink, Category category, boolean isExclusive) {
        super(title, contentId, fileLink, category, isExclusive);
        this.scheduledTime=new Date();
        this.viewers=0;
    }

    public int getViewers() {
        return viewers;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {this.scheduledTime = scheduledTime; }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }
}

