package View;

import Controller.PlaylistController;

public class PlaylistView {
private PlaylistController playlistController;
    PlaylistView() {
        this.playlistController = PlaylistController.getInstance();
    }

    public void handleCreatePlaylistForUser(String[] parts) {

        String result = playlistController.createPlaylistForUser(parts[2]);
        System.out.println(result);
    }

    public void handleCreatePlaylistForChannel(String[] parts) {
        String result = playlistController.createPlaylistForChannel(parts[2]);
        System.out.println(result);
    }

    public void handleAddToPlaylist(String[] parts) {
            int playlistId = Integer.parseInt(parts[1]);
            int contentId = Integer.parseInt(parts[2]);

            String result = playlistController.addToPlaylist(playlistId, contentId);
            System.out.println(result);

    }


}
