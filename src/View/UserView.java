package View;

import Controller.ContentController;
import Controller.UserController;

public class UserView {
    private UserController userController;
    UserView(){
        this.userController=UserController.getInstance();
    }

    public String handleFavoriteCategories(String input) {
        String result = userController.setFavoriteCategories(input);
        System.out.println(result);
        return result;
    }
}
