package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerFirstScreen {
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("activity_main_menu");
    }
}
