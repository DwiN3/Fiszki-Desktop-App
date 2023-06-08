/**
 * This module defines dependencies and specifies the open and exported packages for the application.
 */
module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;
    requires okhttp3;

    opens app to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens Retrofit.Models;
    opens Retrofit.JsonPlaceholderAPI;
    exports Controllers;
    exports app;
}
