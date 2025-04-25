package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.ContentController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.Category;
import com.example.videoplayer.Model.ContentPck.VideoFormat;
import com.example.videoplayer.Model.ContentPck.VideoResolution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class PublishPanel {

    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField durationField;

    @FXML private TextField musicRefField;
    @FXML private DatePicker schedulePicker;

    @FXML private ImageView catMusic, catEducation, catGaming;
    @FXML private ImageView res720p, res1080p;
    @FXML private ImageView formatMP4, formatMKV;

    private Category selectedCategory;
    private VideoResolution selectedResolution;
    private VideoFormat selectedFormat;

    @FXML
    void initialize() {

    }

    @FXML void selectCatMusic(MouseEvent event) {
        selectedCategory = Category.;
        highlightCategory(catMusic);
    }

    @FXML void selectCatEducation(MouseEvent event) {
        selectedCategory = Category.;
        highlightCategory(catEducation);
    }

    @FXML void selectCatGaming(MouseEvent event) {
        selectedCategory = Category.;
        highlightCategory(catGaming);
    }

    private void highlightCategory(ImageView selected) {
        catMusic.setEffect(null);
        catEducation.setEffect(null);
        catGaming.setEffect(null);
        DropShadow glow = new DropShadow(20, Color.BLUE);
        selected.setEffect(glow);
    }

    @FXML void select720p(MouseEvent event) {
        selectedResolution = VideoResolution.;
        highlightResolution(res720p);
    }

    @FXML void select1080p(MouseEvent event) {
        selectedResolution = VideoResolution.;
        highlightResolution(res1080p);
    }

    private void highlightResolution(ImageView selected) {
        res720p.setEffect(null);
        res1080p.setEffect(null);
        DropShadow glow = new DropShadow(20, Color.GREEN);
        selected.setEffect(glow);
    }

    @FXML void selectFormatMP4(MouseEvent event) {
        selectedFormat = VideoFormat.MP4;
        highlightFormat(formatMP4);
    }

    @FXML void selectFormatMKV(MouseEvent event) {
        selectedFormat = VideoFormat.MKV;
        highlightFormat(formatMKV);
    }

    private void highlightFormat(ImageView selected) {
        formatMP4.setEffect(null);
        formatMKV.setEffect(null);
        DropShadow glow = new DropShadow(20, Color.ORANGE);
        selected.setEffect(glow);
    }

    private File selectedFile;
    private File selectedThumbnail;

    @FXML
    void handleChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("انتخاب فایل محتوا");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ویدیو / صوت", "*.mp4", "*.mp3", "*.wav", "*.avi")
        );
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        } else {
            filePathLabel.setText("هیچ فایلی انتخاب نشده");
        }
    }

    @FXML
    void handleChooseThumbnail(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("انتخاب کاور تصویر");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("تصاویر", "*.jpg", "*.jpeg", "*.png")
        );
        selectedThumbnail = fileChooser.showOpenDialog(null);
        if (selectedThumbnail != null) {
            thumbnailPathLabel.setText(selectedThumbnail.getAbsolutePath());
        } else {
            thumbnailPathLabel.setText("هیچ فایلی انتخاب نشده");
        }
    }

    @FXML
    void publishPodcast(ActionEvent event) {
        if (selectedFile == null || selectedThumbnail == null) {
            showAlert("لطفاً فایل محتوا و کاور را انتخاب کنید.");
            return;
        }

        ContentController controller = new ContentController();
        Account user = AuthController.getInstance().getLoggedInUser();

        String msg = controller.publishPodcast(
                ContentSpecialStatus.PODCAST,
                titleField.getText(),
                descriptionField.getText(),
                Integer.parseInt(durationField.getText()),
                selectedCategory,
                selectedFile.getAbsolutePath(),
                selectedThumbnail.getAbsolutePath(),
                user.getFullName()
        );
        showAlert(msg);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
