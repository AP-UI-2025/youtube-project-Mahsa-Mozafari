package Model.AccountPck;

import Model.Category;

import java.util.ArrayList;

public class RegularUser extends User {
    private final int MAX_CONTENT_PER_PLAYLIST;
    private final int MAX_PLAYLIST;

    public RegularUser(String username, String fullName, String phoneNumber, String email, ArrayList<Category> favoriteCategories, String profileCover) {
        super(username, fullName, phoneNumber, email, favoriteCategories, profileCover);
        this.MAX_CONTENT_PER_PLAYLIST=10;
        this.MAX_PLAYLIST=3;
    }


    public int getMAX_CONTENT_PER_PLAYLIST() {
        return MAX_CONTENT_PER_PLAYLIST;
    }

    public int getMAX_PLAYLIST() {
        return MAX_PLAYLIST;
    }


}

