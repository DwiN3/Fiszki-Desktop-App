package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControllerFirstScreen {
    
    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_register");
    }
}
