package Model.ContentPck;

import Model.Category;
import Model.Comment;

import java.util.ArrayList;
import java.util.Date;


public abstract class Content{
    private static int idCounter=1;
    private int contentId;
    private int likes;
    private int views;
    private int duration;
    private String title;
    private String thumbnail;
    private String fileLink;
    private String description;
    private Date publishDate;
    private Category category;
    private ArrayList<Comment> comments;
    private ContentSpecialStatus isExclusive;

    public Content(ContentSpecialStatus isExclusive ,String title, String description,int duration, Category category,String fileLink,String thumbnail){
        this.contentId=idCounter++;
        this.likes=0;
        this.views=0;
        this.duration=0;
        this.thumbnail =thumbnail;
        this.description=description;
        this.fileLink=fileLink;
        this.category=category;
        this.isExclusive=isExclusive;
        this.publishDate=new Date();
        this.comments=new ArrayList<>();

    }

    public int getDuration() {
        return duration;
    }

    public int getContentId() {
        return contentId;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getFileLink() {
        return fileLink;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ContentSpecialStatus getIsExclusive() {
        return isExclusive;
    }

    public void setIsExclusive(ContentSpecialStatus isExclusive) {
        this.isExclusive = isExclusive;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public static void setIdCounter(int idCounter) {
        Content.idCounter = idCounter;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViews(int views) {
        this.views = views;
    }

}


