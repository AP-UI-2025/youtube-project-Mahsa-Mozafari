package View;

import Controller.PlaylistController;

public class PlaylistView {
private PlaylistController playlistController;
    PlaylistView() {
        this.playlistController = PlaylistController.getInstance();
    }

    public void handleCreatePlaylistForUser(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }

        String result = playlistController.createPlaylistForUser(parts[2]);
        System.out.println(result);
    }

    public void handleCreatePlaylistForChannel(String[] parts) {

        if (parts.length < 3) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }

        String result = playlistController.createPlaylistForChannel(parts[2]);
        System.out.println(result);
    }

    public void handleAddToPlaylist(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Provide all required fields.");
            return;
        }

            int playlistId = Integer.parseInt(parts[1]);
            int contentId = Integer.parseInt(parts[2]);

            String result = playlistController.addToPlaylist(playlistId, contentId);
            System.out.println(result);

    }


}
