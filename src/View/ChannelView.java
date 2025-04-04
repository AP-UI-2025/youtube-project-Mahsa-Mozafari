package View;

import Controller.ChannelController;

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

        switch (result) {
            case "Only regular users can create channels.":
                System.out.println("Only regular users can create channels.");
                break;
            case "Channel created successfully.":
                System.out.println("Channel created successfully.");
                break;
            default:
                System.out.println("An unexpected error occurred.");
        }
    }
}
