package Controllers;

import java.io.IOException;

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

public class ControllerResetPassword {
    @FXML
    private Label info_reset_password;
    @FXML
    private TextField email_reset, password_reset, password_re_reset;
    @FXML
    private Button reset_button_reset, back_button_reset;
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    public void initialize(){
        reset_button_reset.setOnAction(event -> {
            info_reset_password.setVisible(false);
            blockButtons(true);
            resetPasswordRetrofit();
        });
        back_button_reset.setOnAction(event -> {
            try {
                switchActivity("activity_main_menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void resetPasswordRetrofit(){
        String emailString = String.valueOf(email_reset.getText());
        String passwordString= String.valueOf(password_reset.getText());
        String passwordReString= String.valueOf(password_re_reset.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        Register post = new Register(passwordString, passwordReString);
        Call<Register> call = jsonUser.resetPassword(post);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.code() == 200){
                    Platform.runLater(() -> {
                        info_reset_password.setStyle("-fx-text-fill: #00FF00;");
                        info_reset_password.setText("Udało się pomyślnie zmienić hasło");
                        info_reset_password.setVisible(true);
                        blockButtons(false);
                    });
                }

                if(!response.isSuccessful()){
                    Platform.runLater(() -> {
                        info_reset_password.setStyle("-fx-text-fill: #FF0000;");
                        info_reset_password.setText("Błędne dane");
                        info_reset_password.setVisible(true);
                        blockButtons(false);
                    });
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                if(t.getMessage().equals("timeout")){
                    Platform.runLater(() -> {
                        info_reset_password.setStyle("-fx-text-fill: #FF0000;");
                        info_reset_password.setText("Uruchamianie serwera");
                        info_reset_password.setVisible(true);
                        blockButtons(false);
                    });
                }
            }
        });
    }
    private void blockButtons(boolean isLoading){
        double buttonOpacity = isLoading ? 1.0 : 1.0;
        reset_button_reset.setDisable(isLoading);
        reset_button_reset.setOpacity(buttonOpacity);
        back_button_reset.setDisable(isLoading);
        back_button_reset.setOpacity(buttonOpacity);
    }
}
