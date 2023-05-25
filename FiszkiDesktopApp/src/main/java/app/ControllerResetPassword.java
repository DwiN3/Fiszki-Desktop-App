package app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerResetPassword {
    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }

}
