
package com.example.videoplayer.Panel;

import com.example.videoplayer.Controller.AuthController;
import com.example.videoplayer.Controller.ChannelController;
import com.example.videoplayer.Controller.ContentController;
import com.example.videoplayer.Model.AccountPck.Account;
import com.example.videoplayer.Model.Category;
import com.example.videoplayer.Model.ContentPck.ContentSpecialStatus;
import com.example.videoplayer.Model.ContentPck.VideoFormat;
import com.example.videoplayer.Model.ContentPck.VideoResolution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;


import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import java.io.File;

public class PublishPanel {

    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField durationField;
    @FXML
    private Label filePathLabel;
    @FXML
    private Label thumbnailPathLabel;

    @FXML private TextField musicRefField;
    @FXML private DatePicker schedulePicker;

    @FXML private ComboBox<String> categoryComboBox;
    @FXML private ComboBox<String> resolutionComboBox;
    @FXML private ComboBox<String> formatComboBox;
    @FXML private ComboBox<String> specialStatusComoBox;

    private Category selectedCategory;
    private VideoResolution selectedResolution;
    private VideoFormat selectedFormat;
    private ContentSpecialStatus selectedStatus;

    private File selectedFile;
    private File selectedThumbnail;

    @FXML
    void initialize() {
        categoryComboBox.getItems().addAll(" News","Game","Podcast","Music","Live","Society","History");
        categoryComboBox.setOnAction(event -> selectCategory());

        categoryComboBox.getItems().addAll("Normal","Special");
        categoryComboBox.setOnAction(event -> selectCategory());

        resolutionComboBox.getItems().addAll("480","720p", "1080p");
        resolutionComboBox.setOnAction(event -> selectResolution());

        formatComboBox.getItems().addAll(" MP4","MKV","MOV","WMV");
        formatComboBox.setOnAction(event -> selectFormat());
    }

    private void selectCategory() {
        String category = categoryComboBox.getValue();
        if ("Music".equals(category)) {
            selectedCategory = Category.Music;
        } else if ("Game".equals(category)) {
            selectedCategory = Category.Game;
        } else if ("History".equals(category)) {
            selectedCategory = Category.History;
        } else if ("Live".equals(category)) {
            selectedCategory = Category.Live;
        } else if ("News".equals(category)) {
            selectedCategory = Category.News;
        } else if ("Podcast".equals(category)) {
            selectedCategory = Category.Podcast;
        } else if ("Society".equals(category)) {
            selectedCategory = Category.Society;
        }
    }


    private void selectResolution() {
        String resolution = resolutionComboBox.getValue();
        if ("480".equals(resolution)) {
            selectedResolution = VideoResolution.LOW;
        } else if ("720".equals(resolution)) {
            selectedResolution = VideoResolution.MEDIUM;
        }else if ("1080".equals(resolution)) {
            selectedResolution = VideoResolution.HIGH;
        }
    }


    private void selectFormat() {
        String format = formatComboBox.getValue();
        if ("MP4".equals(format)) {
            selectedFormat = VideoFormat.MP4;
        } else if ("MKV".equals(format)) {
            selectedFormat = VideoFormat.MKV;
        }else if ("MOV".equals(format)) {
            selectedFormat = VideoFormat.MOV;
        }else if ("WMV".equals(format)) {
            selectedFormat = VideoFormat.WMV;
        }
    }

    private void selectSpecialStatus() {
        String status = specialStatusComoBox.getValue();
        if ("Special".equals(status)) {
            selectedStatus = ContentSpecialStatus.SPECIAL;
        } else if ("Normal".equals(status)) {
           selectedStatus = ContentSpecialStatus.NOT_SPECIAL;
        }
    }

    @FXML
    void handleChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Content");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("video/audio", "*.mp4", "*.mp3", "*.wav", "*.avi")
        );
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void handleChooseThumbnail(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Cover");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.jpeg", "*.png")
        );
        selectedThumbnail = fileChooser.showOpenDialog(null);
        if (selectedThumbnail != null) {
            thumbnailPathLabel.setText(selectedThumbnail.getAbsolutePath());
       }
    }

    @FXML
    void publishPodcast(ActionEvent event) {
        Account user = AuthController.getInstance().getLoggedInUser();
        String msg = ChannelController.getInstance().publishPodcast(
                selectedStatus,
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


