package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import Other.GameSettings;

import java.io.IOException;
import java.io.InputStream;

public class ControllerQuizGame {
    @FXML
    private Label word_text_quiz, word_sample_text_quiz, flashcards_left_quiz;
    @FXML
    private Button answer_1_quiz, answer_2_quiz, answer_3_quiz, answer_4_quiz, next_word_button_quiz, back_menu_button_quiz;
    @FXML
    private ImageView image_quiz, image_word_quiz;
<<<<<<< Updated upstream
    private String answer = "", category="";
    private Image imageWord;
    private int countScore=0, countWords=0, scoreTrain = 0, bestTrain=0;
    private GameSettings gameSettings = GameSettings.getInstance();
=======
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private Image imageWord;
    private SetGame game;
    private int points =0, nrWords =0, scoreTrain = 0, bestTrain=0, allWords;
    private String selectedName = "", selectedData = "category", selectedLanguage = "pl",answer = "";
    private boolean markTheAnswer = false;
    private ArrayList<ModelShowKitsEdit> wordsListKit = new ArrayList<>();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
>>>>>>> Stashed changes
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/drawable/square_big.png"));
        image_quiz.setImage(image);
        category = gameSettings.getCategory();
        System.out.println(category);
        setEmoji();
        setWord();
        flashcards_left_quiz.setText("Fiszki:  "+countScore+"/"+countWords);

        answer_1_quiz.setOnAction(event -> {
            blockButtons(true);
            next_word_button_quiz.setVisible(true);
            next_word_button_quiz.setDisable(false);
            if (answer.equals(answer_1_quiz.getText())) {
                answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_1_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
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
                answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_2_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
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
                answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_3_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_4_quiz.getText())) {
                    answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
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
                answer_4_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                correctChoice();
            } else {
                inCorrectChoice();
                answer_4_quiz.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                if (answer.equals(answer_1_quiz.getText())) {
                    answer_1_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_2_quiz.getText())) {
                    answer_2_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (answer.equals(answer_3_quiz.getText())) {
                    answer_3_quiz.setStyle("-fx-background-color: green; -fx-text-fill: white;");
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
            setWord();
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

<<<<<<< Updated upstream
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
=======
    public void clearButtons() {
        answer_1_quiz.setStyle("");
        answer_2_quiz.setStyle("");
        answer_3_quiz.setStyle("");
        answer_4_quiz.setStyle("");
    }

    void setQuestion(int numberWord) {
        Platform.runLater(() -> {
            markTheAnswer = false;
            word_text_quiz.setText(game.getNameWord(numberWord));
            answer_1_quiz.setText(game.getAns1(numberWord));
            answer_2_quiz.setText(game.getAns2(numberWord));
            answer_3_quiz.setText(game.getAns3(numberWord));
            answer_4_quiz.setText(game.getAns4(numberWord));
            answer = game.getCorrectANS(numberWord);
            word_sample_text_quiz.setText(game.getSentense(numberWord));
        });
    }

    private void getWordFromCateogryRetrofit() {
        wordsListKit.clear();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + tokenInstance.getToken())
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
                        processElements(elementLists);
                        game = new SetGame(selectedData,"quiz", "pl", wordsListKit);
                        scoreTrain = 0;
                        nrWords = 0;
                        allWords = game.getListSize();
                        Platform.runLater(() -> {
                            userPKT_quiz.setText("Punkty:    " + String.valueOf(points) + "/" + String.valueOf(allWords));
                            sticks_left_quiz.setText("Pozostało: "+game.getBorrder());
                        });
                        setEmoji();
                        setQuestion(nrWords);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<List<FlashcardID>>> call, Throwable t) {}
        });
>>>>>>> Stashed changes
    }


    private void setWord(){
        answer = "dog";
        word_text_quiz.setText("pies");
        word_sample_text_quiz.setText("Najlepszy przyjaciel człowieka");
        answer_1_quiz.setText("dog");
        answer_2_quiz.setText("snail");
        answer_3_quiz.setText("rabbit");
        answer_4_quiz.setText("lion");
        countWords += 1;
    }

    private void setInfo(){
        gameSettings.setScoreWords(countScore);
        gameSettings.setAllWords(countWords);
        gameSettings.setPointsForGame(countScore*10);
    }
    private void blockButtons(boolean isLoading){
        answer_1_quiz.setDisable(isLoading);
        answer_2_quiz.setDisable(isLoading);
        answer_3_quiz.setDisable(isLoading);
        answer_4_quiz.setDisable(isLoading);
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

    private void correctChoice() {
        Platform.runLater(() -> {
            points += 1;
            if (scoreTrain < 0) scoreTrain = 1;
            else scoreTrain += 1;
            if (scoreTrain > bestTrain) bestTrain = scoreTrain;
            setEmoji();
            markTheAnswer = true;
            userPKT_quiz.setText("Punkty:    " + points + "/" + allWords);
            word_sample_text_quiz.setText(game.getSentenseTra(nrWords));
            next_word_button_quiz.setVisible(true);
            blockButtons(true);
        });
    }

    private void inCorrectChoice() {
        Platform.runLater(() -> {
            if (scoreTrain > 0) scoreTrain = -1;
            else scoreTrain--;
            setEmoji();
            markTheAnswer = true;
            userPKT_quiz.setText("Punkty:    " + points + "/" + allWords);
            word_sample_text_quiz.setText(game.getSentenseTra(nrWords));
            next_word_button_quiz.setVisible(true);
            blockButtons(true);
        });
    }
}
