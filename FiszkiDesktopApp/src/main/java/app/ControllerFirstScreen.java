package app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerFirstScreen {

    @FXML
    private Button login_button_first, register_button_first, reset_button_first;

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    public void initialize(){
        login_button_first.setOnAction(event -> {
            // Obsługa zdarzenia kliknięcia przycisku logowania
        });

        register_button_first.setOnAction(event -> {
            try {
                switchActivity("activity_register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        reset_button_first.setOnAction(event -> {
            try {
                switchActivity("activity_reset_password");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
