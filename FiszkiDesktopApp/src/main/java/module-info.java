module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires gson;

    opens app to javafx.fxml;
    exports app;
}