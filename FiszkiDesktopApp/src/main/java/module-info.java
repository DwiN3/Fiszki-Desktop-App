module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;

    opens app to javafx.fxml;
    opens Retrofit;
    exports app;
}