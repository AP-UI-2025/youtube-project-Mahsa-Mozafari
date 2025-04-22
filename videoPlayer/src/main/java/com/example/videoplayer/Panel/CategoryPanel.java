package com.example.videoplayer.Panel;
import com.example.videoplayer.Controller.UserController;
import com.example.videoplayer.Model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CategoryPanel {
    public static Stage ctrlStage;

    @FXML
    private GridPane categoryGrid;

    @FXML
    private Button submitBtn;

    private ArrayList<String> selectedCategories = new ArrayList<>();

    @FXML
    public void initialize() {
        int row = 0;
        int col = 0;

        for (Category category : Category.values()) {
            String categoryName = category.name();

            Button button = new Button(categoryName);
            button.setPrefSize(150, 80);
            button.setStyle("-fx-border-color: gray; -fx-font-size: 14px;");

            button.setOnAction(event -> {
                if (selectedCategories.contains(categoryName)) {
                    selectedCategories.remove(categoryName);
                    button.setStyle("-fx-border-color: gray; -fx-font-size: 14px; -fx-background-color: white;");
                } else {
                    if (selectedCategories.size() >= 4) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Too many categories");
                        alert.setContentText("You can only select up to 4 categories.");
                        alert.show();
                        return;
                    }
                    selectedCategories.add(categoryName);
                    button.setStyle("-fx-border-color: #0078D7; -fx-font-size: 14px; -fx-background-color: #0078D7; -fx-text-fill: white;");
                }
            });

            categoryGrid.add(button, col, row);

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    void submitAct(ActionEvent event) throws IOException {
        if (selectedCategories.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No selection");
            alert.setContentText("Please select at least one category.");
            alert.show();
            return;
        }

        String input = String.join(",", selectedCategories);
        String result = UserController.getInstance().setFavoriteCategories(input);

        if (result.equals("Categories updated.")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoplayer/login-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            LoginPanel.ctrlStage.setScene(scene);
            LoginPanel.ctrlStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(result);
            alert.show();
        }
    }
}