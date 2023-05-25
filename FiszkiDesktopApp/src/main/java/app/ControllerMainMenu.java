package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControllerMainMenu {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("activity_first_screen");
    }
}