package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Other.GameSettingsInstance;

import java.io.IOException;

public class ControllerEndScreen {
    @FXML
    private Label score_end,get_points_end,lvl_profile_end,points_to_next_LVL_profile_end, category_end, best_train_score_end;
    @FXML
    private Button back_to_menu_button_profile_end;
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }
    private int scoreEnd, getPoints,  level, pointsToNextLVL, pointsBorder, lenWords, bestTrainScore;
    private String category;
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();


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
        category = gameSettingsInstance.getCategory();
        scoreEnd = gameSettingsInstance.getScoreWords();
        lenWords = gameSettingsInstance.getAllWords();
        getPoints = gameSettingsInstance.getPointsForGame();
        bestTrainScore = gameSettingsInstance.getBestTrain();
        level = 2;
        pointsToNextLVL = 290;
        pointsBorder = 500;
    }

    private void setTextFields(){
        category_end.setText("Kategoria: "+category);
        best_train_score_end.setText("Najlepsza passa: "+bestTrainScore);
        score_end.setText("Poprawne odpowiedzi:  "+scoreEnd+"/"+lenWords);
        get_points_end.setText("Zdobytyte punkty:  "+getPoints+" pkt");
        lvl_profile_end.setText("Poziom:  "+level+" lvl");
        points_to_next_LVL_profile_end.setText("Następny poziom:  "+pointsToNextLVL+"/"+pointsBorder+" pkt");
    }

    private void clearInfo(){
        gameSettingsInstance.setCategory("");
        gameSettingsInstance.setTypeGame("");
        gameSettingsInstance.setScoreWords(0);
        gameSettingsInstance.setAllWords(0);
        gameSettingsInstance.setPointsForGame(0);
        gameSettingsInstance.setBestTrain(0);
    }
}
