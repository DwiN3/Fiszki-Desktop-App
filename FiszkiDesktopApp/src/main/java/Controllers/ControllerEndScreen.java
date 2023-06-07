package Controllers;

import Other.TokenInstance;
import Retrofit.JsonPlaceholderAPI.JsonUser;
import Retrofit.Models.UserLVL;
import app.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Other.GameSettingsInstance;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ControllerEndScreen {
    @FXML
    private Label score_end,get_points_end,lvl_profile_end,points_to_next_LVL_profile_end, category_end, best_train_score_end;
    @FXML
    private Button back_to_menu_button_profile_end;
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }
    private int scoreEnd, points, allWords, bestTrainScore;
    private String category;
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();


    public void initialize() {
        getInfo();
        sendPoints();

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
        category = gameSettingsInstance.getName();
        scoreEnd = gameSettingsInstance.getPoints();
        allWords = gameSettingsInstance.getAllWords();
        points = gameSettingsInstance.getPoints()*10;
        bestTrainScore = gameSettingsInstance.getBestTrain();
    }

    private void clearInfo(){
        gameSettingsInstance.setName("");
        gameSettingsInstance.setGameMode("");
        gameSettingsInstance.setPoints(0);
        gameSettingsInstance.setAllWords(0);
        gameSettingsInstance.setBestTrain(0);
    }

    private void sendPoints() {
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
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        UserLVL pkt = new UserLVL(points);
        Call<UserLVL> call = jsonUser.points("1", pkt);

        call.enqueue(new Callback<UserLVL>() {
            @Override
            public void onResponse(Call<UserLVL> call, Response<UserLVL> response) {
                if(response.isSuccessful()){
                    //System.out.println("Wysłano "+points);
                }
            }

            @Override
            public void onFailure(Call<UserLVL> call, Throwable t) {
                if(t.getMessage().equals("timeout")){
                    //Toast.makeText(ActivityQuizEnd.this,"Uruchamianie serwera", Toast.LENGTH_SHORT).show();
                }
                else{
                    getUserLVL();
                }
            }
        });
    }

    private void getUserLVL() {
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
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        Call<UserLVL> call = jsonUser.getUserLVL();


        call.enqueue(new Callback<UserLVL>() {
            @Override
            public void onResponse(Call<UserLVL> call, Response<UserLVL> response) {
                Platform.runLater(() -> {
                    category_end.setText("Kategoria: " + category);
                    best_train_score_end.setText("Najlepsza passa: " + bestTrainScore);
                    score_end.setText("Poprawne odpowiedzi:  " + scoreEnd + "/" + allWords);
                    get_points_end.setText("Zdobytyte punkty:  " + points + " pkt");
                    lvl_profile_end.setText("Poziom:  " + response.body().getLevel() + " lvl");
                    points_to_next_LVL_profile_end.setText("Następny poziom:  "+response.body().getPoints() + "/" + response.body().getRequiredPoints() + " pkt");
                });
            }

            @Override
            public void onFailure(Call<UserLVL> call, Throwable t) {
                if(t.getMessage().equals("timeout")){
                    //Toast.makeText(ActivityQuizEnd.this,"Uruchamianie serwera", Toast.LENGTH_SHORT).show();
                }

                else{
                }
            }
        });
    }
}
