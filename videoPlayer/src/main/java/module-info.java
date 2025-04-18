module com.example.videoplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens com.example.videoplayer to javafx.fxml;
    exports com.example.videoplayer;
    exports com.example.videoplayer.Model;
    exports com.example.videoplayer.Controller;
    exports com.example.videoplayer.View;

    opens com.example.videoplayer.Model to javafx.fxml;
    opens com.example.videoplayer.View to javafx.fxml;

    opens com.example.videoplayer.Controller to javafx.fxml;
    exports com.example.videoplayer.ViewController;
    opens com.example.videoplayer.ViewController to javafx.fxml;
}