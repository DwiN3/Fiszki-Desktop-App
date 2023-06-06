package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import Other.GameSettingsInstance;

import java.io.IOException;
import java.io.InputStream;

public class ControllerQuizGame {
    @FXML
    private Label word_text_quiz, word_sample_text_quiz, flashcards_left_quiz;
    @FXML
    private Button answer_1_quiz, answer_2_quiz, answer_3_quiz, answer_4_quiz, next_word_button_quiz, back_menu_button_quiz;
    @FXML
    private ImageView image_quiz, image_word_quiz;
    private String answer = "", category="";
    private Image imageWord;
    private int countScore=0, countWords=0, scoreTrain = 0, bestTrain=0, border=10;
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/drawable/square_big.png"));
        image_quiz.setImage(image);
        category = gameSettingsInstance.getCategory();
        System.out.println(category);
        setEmoji();
        setWord();

        flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
        next_word_button_quiz.setVisible(false);
        next_word_button_quiz.setDisable(true);

        answer_1_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_1_quiz.getText())) {
                answer_1_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_1_quiz.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
                if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                }
            }
            flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
            setInfo();
        });

        answer_2_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_2_quiz.getText())) {
                answer_2_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_2_quiz.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                }
            }
            flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
            setInfo();
        });

        answer_3_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_3_quiz.getText())) {
                answer_3_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_3_quiz.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                }
            }
            flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
            setInfo();
        });

        answer_4_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_4_quiz.getText())) {
                answer_4_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_4_quiz.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white;");
                }
            }
            setInfo();
        });

        next_word_button_quiz.setOnAction(event -> {
            blockButtons(false);
            next_word_button_quiz.setVisible(false);
            next_word_button_quiz.setDisable(true);
            answer_1_quiz.setStyle("");
            answer_2_quiz.setStyle("");
            answer_3_quiz.setStyle("");
            answer_4_quiz.setStyle("");
            String resourcePath = "/drawable/flashcard_icon_png.png";
            Image nextImage = new Image(getClass().getResourceAsStream(resourcePath));
            image_word_quiz.setImage(nextImage);
            if(!next_word_button_quiz.getText().equals("Podsumowanie")) setWord();
            else{
                try {
                    switchActivity("activity_end_screen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        back_menu_button_quiz.setOnAction(event -> {
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
                image_word_quiz.setImage(imageWord);
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
        countScore += 1;
        if(scoreTrain < 0) scoreTrain = 1;
        else scoreTrain += 1;
        if(scoreTrain > bestTrain) bestTrain = scoreTrain;
        setEmoji();
        flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
    }

    private void inCorrectChoice(){
        if (scoreTrain > 0) scoreTrain = -1;
        else scoreTrain--;
        setEmoji();
        flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);
    }


    private void setWord(){
        countWords++;
        answer = "dog";
        word_text_quiz.setText("pies");
        word_sample_text_quiz.setText("Najlepszy przyjaciel człowieka");
        answer_1_quiz.setText("dog");
        answer_2_quiz.setText("snail");
        answer_3_quiz.setText("rabbit");
        answer_4_quiz.setText("lion");
    }

    private void setInfo(){
        if (countWords == border) next_word_button_quiz.setText("Podsumowanie");
        gameSettingsInstance.setScoreWords(countScore);
        gameSettingsInstance.setAllWords(countWords);
        gameSettingsInstance.setPointsForGame(countScore*10);
        gameSettingsInstance.setBestTrain(bestTrain);
    }
    private void blockButtons(boolean isLoading){
        double buttonOpacity = isLoading ? 1.0 : 1.0;
        answer_1_quiz.setDisable(isLoading);
        answer_1_quiz.setOpacity(buttonOpacity);
        answer_2_quiz.setDisable(isLoading);
        answer_2_quiz.setOpacity(buttonOpacity);
        answer_3_quiz.setDisable(isLoading);
        answer_3_quiz.setOpacity(buttonOpacity);
        answer_4_quiz.setDisable(isLoading);
        answer_4_quiz.setOpacity(buttonOpacity);
    }
}
