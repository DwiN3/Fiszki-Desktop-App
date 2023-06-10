package Controllers;

import Retrofit.JsonPlaceholderAPI.JsonUser;
import Retrofit.Models.Register;
import app.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ControllerPasswordReminder {
    @FXML
    private Label info_password_reminder;
    @FXML
    private TextField email_reminder;
    @FXML
    private Button button_reminder, back_button_reminder;
    @FXML
    private void switchActivity(String activity) throws IOException { App.setRoot(activity); }

    public void initialize(){
        button_reminder.setOnAction(event -> {
            info_password_reminder.setVisible(false);
            blockButtons(true);
            reminderPasswordRetrofit();
        });
        back_button_reminder.setOnAction(event -> {
            try {
                switchActivity("activity_first_screen");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Disables or enables the buttons based on the specified isLoading value
     * @param isLoading true to disable the buttons and show loading state, false otherwise
     */
    public void blockButtons(boolean isLoading){
        double buttonOpacity = isLoading ? 1.0 : 1.0;
        button_reminder.setDisable(isLoading);
        button_reminder.setOpacity(buttonOpacity);
        back_button_reminder.setDisable(isLoading);
        back_button_reminder.setOpacity(buttonOpacity);
    }

    public void reminderPasswordRetrofit(){
        String emailString = String.valueOf(email_reminder.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        Register post = new Register(emailString);
        Call<Register> call = jsonUser.getPassword(post);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.code() == 200){
                    Platform.runLater(() -> {
                        info_password_reminder.setStyle("-fx-text-fill: #00FF00;");
                        info_password_reminder.setText("Hasło zostało wysłane na maila");
                        info_password_reminder.setVisible(true);
                        blockButtons(false);
                    });
                }

                if(!response.isSuccessful()){
                    Platform.runLater(() -> {
                        info_password_reminder.setStyle("-fx-text-fill: #FF0000;");
                        info_password_reminder.setText("Brak maila");
                        info_password_reminder.setVisible(true);
                        blockButtons(false);
                    });
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                if(t.getMessage().equals("timeout")){
                    Platform.runLater(() -> {
                        info_password_reminder.setStyle("-fx-text-fill: #FF0000;");
                        info_password_reminder.setText("Uruchamianie serwera");
                        info_password_reminder.setVisible(true);
                        blockButtons(false);
                    });
                }
            }
        });
    }
}
