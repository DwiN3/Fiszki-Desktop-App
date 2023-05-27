package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ControllerWpisGame {

    @FXML
    private Label word_text_wpis, word_sample_text_wpis, answer_text_wpis;

    @FXML
    private TextField your_word_text_wpis;

    @FXML
    private Button next_word_button_wpis, back_menu_button_wpis;

    @FXML
    private ImageView image_wpis, image_word_wpis;
    private String answer = "";

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/drawable/square.png"));
        image_wpis.setImage(image);

        setWord();

        next_word_button_wpis.setOnAction(event -> {
            if(next_word_button_wpis.getText().equals("Sprawdź")){
                your_word_text_wpis.setVisible(false);
                your_word_text_wpis.setDisable(true);
                answer_text_wpis.setVisible(true);
                answer_text_wpis.setDisable(false);
                if (your_word_text_wpis.getText().equals(answer)) {
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

            }
            else{
                your_word_text_wpis.setVisible(true);
                your_word_text_wpis.setDisable(false);
                answer_text_wpis.setVisible(false);
                answer_text_wpis.setDisable(true);
                your_word_text_wpis.setText("");
                answer_text_wpis.setText("");
                next_word_button_wpis.setText("Sprawdź");
            }
        });
        back_menu_button_wpis.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setWord(){
        answer = "dog";
        word_text_wpis.setText("pies");
        word_sample_text_wpis.setText("Najlepszy przyjaciel człowieka");
        Image imageWord = new Image(getClass().getResourceAsStream("/drawable/word_dog.png"));
        image_word_wpis.setImage(imageWord);
    }
}
