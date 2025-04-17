module com.example.videoplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens com.example.videoplayer to javafx.fxml;
    exports com.example.videoplayer;
    exports com.example.videoplayer.Model;
    opens com.example.videoplayer.Model to javafx.fxml;
    exports com.example.videoplayer.Controller;
    opens com.example.videoplayer.Controller to javafx.fxml;
}