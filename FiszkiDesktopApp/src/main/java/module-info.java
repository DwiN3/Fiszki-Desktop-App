module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;

    opens app to javafx.fxml;
    opens retrofit;
    exports app;
    exports controller;

    opens controller to javafx.fxml;
}
