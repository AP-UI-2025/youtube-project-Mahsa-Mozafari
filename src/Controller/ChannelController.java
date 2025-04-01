package Controller;

import Model.Category;
import Model.Channel;
import Model.ContentPck.ContentSpecialStatus;
import Model.ContentPck.VideoResolution;

import java.util.ArrayList;
import java.util.Date;

public class ChannelController {


    public ArrayList<Channel> createChannel(String channelName, String description, String channelCover) {
        return null;
    }

    public void publishPodcast(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String creator) {

    }

    public void publishNormalVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String subtitle, VideoResolution resolution) {

    }

    public void publishShortVideo(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, String musicReference) {

    }

    public void publishLiveStream(ContentSpecialStatus code, String title, String description, int duration, Category category, String fileLink, String thumbnail, Date scheduledTime) {

    }

    public ArrayList<Channel> searchChannel(String searchBox) {
        return null;
    }

    public Object[] viewChannel(){
        return null;
    }

    public ArrayList<Channel> showChannelContents(int channelId, String channelName){
        return null;
    }

}

