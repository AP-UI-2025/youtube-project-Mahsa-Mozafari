package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.PremiumController;
import com.example.videoplayer.Controller.UserController;
import com.example.videoplayer.Model.AccountPck.PremiumPackage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PremiumPanel {

    @FXML
    private ImageView bronzeImage, silverImage, goldImage;

    @FXML private TextField creditField;

    private PremiumPackage selectedPackage = null;

    @FXML
    public void initialize() {
        bronzeImage.setOnMouseClicked(e -> selectPackage(PremiumPackage.BRONZE, bronzeImage));
        silverImage.setOnMouseClicked(e -> selectPackage(PremiumPackage.SILVER, silverImage));
        goldImage.setOnMouseClicked(e -> selectPackage(PremiumPackage.GOLD, goldImage));
    }

    private void selectPackage(PremiumPackage pkg, ImageView selectedImage) {
        selectedPackage = pkg;

        bronzeImage.setStyle("");
        silverImage.setStyle("");
        goldImage.setStyle("");
        selectedImage.setStyle("-fx-effect: dropshadow(three-pass-box, gold, 10, 0, 0, 0);");
    }

    @FXML
    public void handlePurchase(ActionEvent event) {
        if (selectedPackage == null) {
            showAlert("Please select a package.");
            return;
        }
        String result = UserController.getInstance().buyPremium(selectedPackage);
        showAlert(result);
    }

    @FXML
    public void handleExtend(ActionEvent event) {
        if (selectedPackage == null) {
            showAlert("Please select a package.");
            return;
        }
        String result = PremiumController.getInstance().extendSubscription(selectedPackage);
        showAlert(result);
    }

    @FXML
    public void handleIncreaseCredit(ActionEvent event) {
        try {
            double amount = Double.parseDouble(creditField.getText());
            String result = UserController.getInstance().increaseCredit(amount);
            showAlert(result);
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Status");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
