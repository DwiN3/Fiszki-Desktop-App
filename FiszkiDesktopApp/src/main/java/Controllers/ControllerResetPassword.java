package Controllers;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;

public class ControllerResetPassword {
    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }

}
