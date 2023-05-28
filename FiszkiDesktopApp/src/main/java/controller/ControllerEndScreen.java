package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ControllerEndScreen {
    @FXML
    private Label score_end,get_points_end,lvl_profile_end,points_to_next_LVL_profile_end;

    @FXML
    private Button back_to_menu_button_profile_end;

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }
    private int scoreEnd, getPoints,  level, pointsToNextLVL, pointsBorder, lenWords;


    public void initialize() {
        getInfo();
        setTextFields();
        back_to_menu_button_profile_end.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getInfo(){
        scoreEnd = 3;
        lenWords = 4;
        getPoints = 30;
        level = 2;
        pointsToNextLVL = 290;
        pointsBorder = 500;
    }

    private void setTextFields(){
        score_end.setText("Poprawne odpowiedzi:  "+scoreEnd+"/"+lenWords);
        get_points_end.setText("Zdobytyte punkty:  "+getPoints+" pkt");
        lvl_profile_end.setText("LVL:   "+level);
        points_to_next_LVL_profile_end.setText("Punkty:  "+pointsToNextLVL+"/"+pointsBorder+" pkt");
    }
}
