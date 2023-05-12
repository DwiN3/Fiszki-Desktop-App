package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControllerFirstScreen {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("activity_main_menu");
    }
}
