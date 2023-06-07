package Controllers;

import Other.SetGame;
import Retrofit.JsonPlaceholderAPI.JsonFlashcardsCollections;
import Retrofit.Models.FlashcardID;
import app.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Other.DateInstance;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ControllerWpisGame {
    @FXML
    private Label word_text_wpis, word_sample_text_wpis, answer_text_wpis,  sticks_left_wpis, userPKT_wpis;
    @FXML
    private TextField your_word_text_wpis;
    @FXML
    private Button next_word_button_wpis, back_menu_button_wpis;
    @FXML
    private ImageView image_wpis, image_word_wpis;
    @FXML
    private void switchActivity(String activity) throws IOException { App.setRoot(activity); }
    private Image imageWord;
    private DateInstance dateInstance = DateInstance.getInstance();
    private SetGame game;
    private int points = 0, nrWords = 0, scoreTrain = 0, bestTrain = 0, allWords = 0;
    private String selectedLanguage = "pl", selectedName = "", selectedData = "category", answer = "";
    private ArrayList<FlashcardID> wordsListKit = new ArrayList<>();

    @FXML
    private void initialize() {
        Image imageBackgroundStart = new Image(getClass().getResourceAsStream("/drawable/square_big.png"));
        image_wpis.setImage(imageBackgroundStart);
        Image imageIconStart = new Image(getClass().getResourceAsStream("/drawable/flashcard_icon_png.png"));
        image_word_wpis.setImage(imageIconStart);

        selectedName = dateInstance.getName();
        getWordFromCateogryRetrofit();

        next_word_button_wpis.setOnAction(event -> {
            if (next_word_button_wpis.getText().equals("Podsumowanie")) {
                dateInstance.setBestTrain(bestTrain);
                dateInstance.setPoints(points);
                dateInstance.setAllWords(allWords);
                try {
                    switchActivity("activity_end_screen");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (next_word_button_wpis.getText().equals("Sprawdź")) {
                your_word_text_wpis.setVisible(false);
                your_word_text_wpis.setDisable(true);
                answer_text_wpis.setVisible(true);
                answer_text_wpis.setDisable(false);

                if (your_word_text_wpis.getText().equals(answer)) {
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: #00FF00;");
                    correctChoice();
                } else {
                    next_word_button_wpis.setText("Następne słowo");
                    answer_text_wpis.setText("Tłumaczenie to: " + answer);
                    answer_text_wpis.setStyle("-fx-text-fill: FF0000;");
                    inCorrectChoice();
                }

                if (nrWords == game.getBorrder() - 1) {
                    next_word_button_wpis.setText("Podsumowanie");
                }
                sticks_left_wpis.setText("Pozostało: " + (game.getBorrder() - nrWords-1));
            } else {
                nrWords += 1;
                setQuestion(nrWords);
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
        });

        back_menu_button_wpis.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void correctChoice(){
        Platform.runLater(() -> {
            points += 1;
            if (scoreTrain < 0) scoreTrain = 1;
            else scoreTrain += 1;
            if (scoreTrain > bestTrain) bestTrain = scoreTrain;
            setEmoji();
            userPKT_wpis.setText("Punkty:    " + points + "/" + allWords);
            word_sample_text_wpis.setText(game.getSentenseTra(nrWords));
            next_word_button_wpis.setVisible(true);
            next_word_button_wpis.setDisable(false);
        });
    }

    private void inCorrectChoice(){
        Platform.runLater(() -> {
            if (scoreTrain > 0) scoreTrain = -1;
            else scoreTrain--;
            setEmoji();
            userPKT_wpis.setText("Punkty:    " + points + "/" + allWords);
            word_sample_text_wpis.setText(game.getSentenseTra(nrWords));
            next_word_button_wpis.setVisible(true);
            next_word_button_wpis.setDisable(false);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setQuestion(int numberWord) {
        Platform.runLater(() -> {
            word_text_wpis.setText(game.getNameWord(numberWord));
            answer = game.getCorrectANS(numberWord);
            word_sample_text_wpis.setText(game.getSentense(numberWord));
        });
    }

    private void getWordFromCateogryRetrofit() {
        wordsListKit.clear();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + dateInstance.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonFlashcardsCollections jsonFlashcardsCollections = retrofit.create(JsonFlashcardsCollections.class);
        Call<List<List<FlashcardID>>> call = jsonFlashcardsCollections.getCategory(selectedName);

        call.enqueue(new Callback<List<List<FlashcardID>>>() {
            @Override
            public void onResponse(Call<List<List<FlashcardID>>> call, Response<List<List<FlashcardID>>> response) {
                if (response.isSuccessful()) {
                    List<List<FlashcardID>> elementLists = response.body();
                    if (elementLists != null) {
                        // Przekazanie elementów do innej metody lub klasy
                        processElements(elementLists);
                        game = new SetGame(selectedData,"quiz", selectedLanguage, wordsListKit);
                        scoreTrain = 0;
                        nrWords = 0;
                        allWords = game.getListSize();
                        Platform.runLater(() -> {
                            userPKT_wpis.setText("Punkty:    " + points + "/" + allWords);
                            sticks_left_wpis.setText("Pozostało: "+game.getBorrder());
                        });
                        setEmoji();
                        setQuestion(nrWords);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<List<FlashcardID>>> call, Throwable t) { }
        });
    }

    private void processElements(List<List<FlashcardID>> elementLists) {
        for (List<FlashcardID> elementList : elementLists) {
            int id_count=0;
            for (FlashcardID element : elementList) {
                wordsListKit.add(new FlashcardID(element.getWord(), element.getTranslatedWord(), element.getExample(), element.getTranslatedExample(), id_count, element.get_id()));
                id_count++;
            }
        }
    }
}
