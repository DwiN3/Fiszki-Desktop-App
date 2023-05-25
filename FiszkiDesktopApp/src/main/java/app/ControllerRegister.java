package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControllerRegister {

    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }
}