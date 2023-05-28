package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.GameSettings;

import java.io.IOException;

public class ControllerWpisGame {
    @FXML
    private Label word_text_wpis, word_sample_text_wpis, answer_text_wpis,  flashcards_left_wpis;
    @FXML
    private TextField your_word_text_wpis;
    @FXML
    private Button next_word_button_wpis, back_menu_button_wpis;
    @FXML
    private ImageView image_wpis, image_word_wpis;
    private String answer = "",category="";
    private int countScore=0, countWords=0;
    private GameSettings gameSettings = GameSettings.getInstance();

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/drawable/square.png"));
        image_wpis.setImage(image);
        category = gameSettings.getCategory();
        setWord();

        next_word_button_wpis.setOnAction(event -> {
            if(next_word_button_wpis.getText().equals("Sprawdź")){
                your_word_text_wpis.setVisible(false);
                your_word_text_wpis.setDisable(true);
                answer_text_wpis.setVisible(true);
                answer_text_wpis.setDisable(false);
                if (your_word_text_wpis.getText().equals(answer)) {
                    countScore += 1;
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: green;");
                    System.out.println("gratulacje");
                } else {
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: red;");
                    System.out.println("złe słowo");
                }
                flashcards_left_wpis.setText("Fiszki:  "+countScore+"/"+countWords);
            }
            else{
                setWord();
                your_word_text_wpis.setVisible(true);
                your_word_text_wpis.setDisable(false);
                answer_text_wpis.setVisible(false);
                answer_text_wpis.setDisable(true);
                your_word_text_wpis.setText("");
                answer_text_wpis.setText("");
                next_word_button_wpis.setText("Sprawdź");
            }
            setInfo();
        });
        back_menu_button_wpis.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setInfo(){
        gameSettings.setScoreWords(countScore);
        gameSettings.setAllWords(countWords);
        gameSettings.setPointsForGame(countScore*10);
    }

    private void setWord() {
        countWords += 1;
        answer = "dog";
        word_text_wpis.setText("pies");
        word_sample_text_wpis.setText("Najlepszy przyjaciel człowieka");
        Image imageWord = new Image(getClass().getResourceAsStream("/drawable/word_dog.png"));
        image_word_wpis.setImage(imageWord);
        flashcards_left_wpis.setText("Fiszki:  " + countScore + "/" + countWords);
    }


}
