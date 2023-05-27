package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.IOException;

public class ControllerQuizGame {

    @FXML
    private Label word_text_quiz, word_sample_text_quiz;

    @FXML
    private Button answer_1_quiz, answer_2_quiz, answer_3_quiz, answer_4_quiz, next_word_button_quiz, back_menu_button_quiz;

    @FXML
    private ImageView image_quiz, image_word_quiz;
    private String answer = "";

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/drawable/square.png"));
        image_quiz.setImage(image);

        setWord();

        answer_1_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_1_quiz.getText())) {
                answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else {
                answer_1_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                }
            }
        });

        answer_2_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_2_quiz.getText())) {
                answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else {
                answer_2_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                }
            }
        });

        answer_3_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_3_quiz.getText())) {
                answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else {
                answer_3_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                }
            }
        });

        answer_4_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_4_quiz.getText())) {
                answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else {
                answer_4_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                }
            }
        });




        next_word_button_quiz.setOnAction(event -> {
            blockButtons(false);
            next_word_button_quiz.setVisible(false);
            next_word_button_quiz.setDisable(true);
            answer_1_quiz.setStyle("");
            answer_2_quiz.setStyle("");
            answer_3_quiz.setStyle("");
            answer_4_quiz.setStyle("");
        });
        back_menu_button_quiz.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setWord(){
        answer = "dog";
        word_text_quiz.setText("pies");
        word_sample_text_quiz.setText("Najlepszy przyjaciel człowieka");
        answer_1_quiz.setText("dog");
        answer_2_quiz.setText("snail");
        answer_3_quiz.setText("rabbit");
        answer_4_quiz.setText("lion");
        Image imageWord = new Image(getClass().getResourceAsStream("/drawable/word_dog.png"));
        image_word_quiz.setImage(imageWord);
    }
    private void blockButtons(boolean isLoading){
        answer_1_quiz.setDisable(isLoading);
        answer_2_quiz.setDisable(isLoading);
        answer_3_quiz.setDisable(isLoading);
        answer_4_quiz.setDisable(isLoading);
    }
}
