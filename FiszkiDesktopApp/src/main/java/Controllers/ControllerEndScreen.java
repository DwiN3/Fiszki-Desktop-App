package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Other.GameSettings;

import java.io.IOException;

public class ControllerEndScreen {
    @FXML
    private Label score_end,get_points_end,lvl_profile_end,points_to_next_LVL_profile_end, category_end;
    @FXML
    private Button back_to_menu_button_profile_end;
    @FXML
<<<<<<< Updated upstream
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }
    private int scoreEnd, getPoints,  level, pointsToNextLVL, pointsBorder, lenWords;
    private String category;
    private GameSettings gameSettings = GameSettings.getInstance();

=======
    private void switchActivity(String activity) throws IOException { App.setRoot(activity); }
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    private int scoreEnd, points, allWords, bestTrainScore;
    private String category;
>>>>>>> Stashed changes

    public void initialize() {
        getInfo();
        setTextFields();
        back_to_menu_button_profile_end.setOnAction(event -> {
            clearInfo();
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getInfo(){
        category = gameSettings.getCategory();
        scoreEnd = gameSettings.getScoreWords();
        lenWords = gameSettings.getAllWords();
        getPoints = gameSettings.getPointsForGame();
        level = 2;
        pointsToNextLVL = 290;
        pointsBorder = 500;
    }

    private void setTextFields(){
        category_end.setText("Kategoria: "+category);
        score_end.setText("Poprawne odpowiedzi:  "+scoreEnd+"/"+lenWords);
        get_points_end.setText("Zdobytyte punkty:  "+getPoints+" pkt");
        lvl_profile_end.setText("Poziom:  "+level+" lvl");
        points_to_next_LVL_profile_end.setText("Następny poziom:  "+pointsToNextLVL+"/"+pointsBorder+" pkt");
    }

    private void clearInfo(){
        gameSettings.setCategory("");
        gameSettings.setTypeGame("");
        gameSettings.setScoreWords(0);
        gameSettings.setAllWords(0);
        gameSettings.setPointsForGame(0);
        level = 0;
        pointsToNextLVL = 0;
        pointsBorder = 0;
    }
}
