package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Other.GameSettingsInstance;

import java.io.IOException;
import java.io.InputStream;

public class ControllerWpisGame {
    @FXML
    private Label word_text_wpis, word_sample_text_wpis, answer_text_wpis,  flashcards_left_wpis;
    @FXML
    private TextField your_word_text_wpis;
    @FXML
    private Button next_word_button_wpis, back_menu_button_wpis;
    @FXML
    private ImageView image_wpis, image_word_wpis;
    private Image imageWord;
    private String answer = "",category="";
    private int countScore=0, countWords=0,points = 0, scoreTrain = 0, bestTrain=0, border=10;;
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image imageBackgroundStart = new Image(getClass().getResourceAsStream("/drawable/square_big.png"));
        image_wpis.setImage(imageBackgroundStart);
        Image imageIconStart = new Image(getClass().getResourceAsStream("/drawable/flashcard_icon_png.png"));
        image_word_wpis.setImage(imageIconStart);

        flashcards_left_wpis.setText("Fiszki:  "+countScore+"/"+countWords);
        next_word_button_wpis.getText().equals("Sprawdź");

        category = gameSettingsInstance.getCategory();
        setWord();

        next_word_button_wpis.setOnAction(event -> {
            if(next_word_button_wpis.getText().equals("Podsumowanie")){
                try {
                    switchActivity("activity_end_screen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(next_word_button_wpis.getText().equals("Sprawdź")){
                your_word_text_wpis.setVisible(false);
                your_word_text_wpis.setDisable(true);
                answer_text_wpis.setVisible(true);
                answer_text_wpis.setDisable(false);
                if (your_word_text_wpis.getText().equals(answer)) {
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: green;");
                    correctChoice();
                    System.out.println("gratulacje");
                }else {
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: red;");
                    inCorrectChoice();
                    System.out.println("złe słowo");
                }
                if (countWords == border) next_word_button_wpis.setText("Podsumowanie");
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
                String resourcePathIcon = "/drawable/flashcard_icon_png.png";
                Image nextImageIcon = new Image(getClass().getResourceAsStream(resourcePathIcon));
                image_word_wpis.setImage(nextImageIcon);
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
    private void setEmoji() {
        String resourcePath;
        if (scoreTrain <= -5)
            resourcePath = "/drawable/emoji_m5.png";
        else if (scoreTrain == -4)
            resourcePath = "/drawable/emoji_m4.png";
        else if (scoreTrain == -3)
            resourcePath = "/drawable/emoji_m3.png";
        else if (scoreTrain == -2)
            resourcePath = "/drawable/emoji_m2.png";
        else if (scoreTrain == -1)
            resourcePath = "/drawable/emoji_m1.png";
        else if (scoreTrain == 0)
            resourcePath = "/drawable/flashcard_icon_png.png";
        else if (scoreTrain == 1)
            resourcePath = "/drawable/emoji_1.png";
        else if (scoreTrain == 2)
            resourcePath = "/drawable/emoji_2.png";
        else if (scoreTrain == 3)
            resourcePath = "/drawable/emoji_3.png";
        else if (scoreTrain == 4)
            resourcePath = "/drawable/emoji_4.png";
        else if (scoreTrain >= 5)
            resourcePath = "/drawable/emoji_5.png";
        else
            resourcePath = "/drawable/flashcard_icon_png.png";

        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream != null) {
                imageWord = new Image(inputStream);
                image_word_wpis.setImage(imageWord);
            } else {
                // Obsłuż brak dostępu do zasobu lub inny problem
                // Możesz ustawić domyślny obraz lub wyrzucić wyjątek, jeśli to odpowiednie dla twojej logiki aplikacji
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Obsłuż wyjątek, jeśli to odpowiednie dla twojej logiki aplikacji
        }
    }

    private void correctChoice(){
        countScore++;
        if(scoreTrain < 0) scoreTrain = 1;
        else scoreTrain += 1;
        if(scoreTrain > bestTrain) bestTrain = scoreTrain;
        setEmoji();
        flashcards_left_wpis.setText("Fiszki:  "+countScore+"/"+countWords);
    }

    private void inCorrectChoice(){
        if (scoreTrain > 0) scoreTrain = -1;
        else scoreTrain--;
        setEmoji();
        flashcards_left_wpis.setText("Fiszki:  "+countScore+"/"+countWords);
    }

    private void setInfo(){

        gameSettingsInstance.setScoreWords(countScore);
        gameSettingsInstance.setAllWords(countWords);
        gameSettingsInstance.setPointsForGame(countScore*10);
        gameSettingsInstance.setBestTrain(bestTrain);
    }

    private void setWord() {
        countWords++;
        answer = "dog";
        word_text_wpis.setText("pies");
        word_sample_text_wpis.setText("Najlepszy przyjaciel człowieka");
        flashcards_left_wpis.setText("Fiszki:  "+countScore+"/"+countWords);
    }
}
