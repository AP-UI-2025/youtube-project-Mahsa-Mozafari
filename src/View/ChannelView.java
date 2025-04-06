package View;

import Controller.ChannelController;
import Controller.UserController;
import Model.Category;
import Model.ContentPck.Content;
import Model.ContentPck.ContentSpecialStatus;
import Model.ContentPck.VideoFormat;
import Model.ContentPck.VideoResolution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChannelView {

    private ChannelController channelController;
    ChannelView(){
        this.channelController=ChannelController.getInstance();
    }
    public void handleCreateChannel(String[] parts) {
        if (parts.length < 4) {
            System.out.println("Invalid command. Usage: CreateChannel - <channelName> - <description> - <cover>");
            return;
        }

        String result = channelController.createChannel(parts[1], parts[2], parts[3]);
        System.out.println(result);
    }

    public void handlePublishPodcast(String[] parts) {
        if (parts.length < 10) {
            System.out.println("Invalid command format");
            return;
        }

            String input = parts[2];
            ContentSpecialStatus code;
            switch (input) {
                case "N":
                    code = ContentSpecialStatus.NOT_SPECIAL;
                    break;
                case "P":
                    code = ContentSpecialStatus.SPECIAL;
                    break;
                default:
                    System.out.println("Invalid content status.");
                    return;
                    }

            String title = parts[3];
            String description = parts[4];
            int duration = Integer.parseInt(parts[5]);
            Category category = Category.valueOf(parts[6].toUpperCase());
            String fileLink = parts[7];
            String thumbnail = parts[8];
            String creator = parts[9];

            String result = channelController.publishPodcast(code, title, description, duration, category, fileLink, thumbnail, creator);
            System.out.println(result);

    }

    public void handlePublishNormalVideo(String[] parts) {
        if (parts.length < 12) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }
        String input = parts[2];
        ContentSpecialStatus code;
        switch (input) {
            case "N":
                code = ContentSpecialStatus.NOT_SPECIAL;
                break;
            case "P":
                code = ContentSpecialStatus.SPECIAL;
                break;
            default:
                System.out.println("Invalid content status.");
                return;
        }
        String inbox=parts[10];
        VideoResolution resolution;
            switch (inbox) {
                case "1080":
                    resolution=VideoResolution.HIGH;
                    break;
                case "720":
                   resolution=VideoResolution.MEDIUM;
                    break;
                case "360":
                    resolution=VideoResolution.LOW;
                    break;
                default:
                    System.out.println("Invalid resolution");
                    return;
            }

        String result = channelController.publishNormalVideo(code, parts[3], parts[4], Integer.parseInt(parts[5]), Category.valueOf(parts[6]), parts[7], parts[8], parts[9],resolution, VideoFormat.valueOf(parts[11].trim().toUpperCase()));
        System.out.println(result);
    }

    public void handlePublishShortVideo(String[] parts) {
        if (parts.length < 11) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }
        ContentSpecialStatus code = ContentSpecialStatus.valueOf(parts[2]);
        String result = channelController.publishShortVideo(code, parts[3], parts[4], Integer.parseInt(parts[5]), Category.valueOf(parts[6]), parts[7], parts[8], parts[9], parts[10]);
        System.out.println(result);
    }

    public void handlePublishLiveStream(String[] parts) {
        if (parts.length < 11) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }
        try {
            ContentSpecialStatus code = ContentSpecialStatus.valueOf(parts[2]);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date scheduledTime = format.parse(parts[10]);
            String result = channelController.publishLiveStream(code, parts[3], parts[4], Integer.parseInt(parts[5]), Category.valueOf(parts[6]), parts[7], parts[8], parts[9], scheduledTime);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Failed to parse date. Use format: yyyy-MM-dd HH:mm");
        }
    }

    public void handleViewChannelInfo(){
        String result = channelController.viewChannelInfo();
        System.out.println(result);
    }

}
