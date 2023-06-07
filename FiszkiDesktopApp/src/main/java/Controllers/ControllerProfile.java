package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Other.Token;

import java.io.IOException;

public class ControllerProfile {
    @FXML
    private Label nick_user_profile,lvl_profile,points_to_next_LVL_profile;
    @FXML
    private Button back_to_menu_button_profile;
<<<<<<< Updated upstream
    private Token token  = Token.getInstance();
    private int lvl = 2, points=256, pointsBorder = 500;

=======
>>>>>>> Stashed changes
    @FXML
    private void switchActivity(String activity) throws IOException { App.setRoot(activity); }
    private TokenInstance tokenInstance = TokenInstance.getInstance();

    public void initialize(){
        nick_user_profile.setText("Login:  "+token.getUserName());
        lvl_profile.setText("Poziom:  "+lvl+" lvl");
        points_to_next_LVL_profile.setText("Następny poziom:  "+points+"/"+pointsBorder+" pkt");
        back_to_menu_button_profile.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
