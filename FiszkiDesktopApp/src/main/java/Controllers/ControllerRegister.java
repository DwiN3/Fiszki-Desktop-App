package Controllers;

import java.io.IOException;

import Retrofit.Models.Register;
import app.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import Retrofit.JsonPlaceholderAPI.JsonUser;

public class ControllerRegister {
    @FXML
    private Label info_register;
    @FXML
    private TextField name_register, email_register;
    @FXML
    private PasswordField password_register, password_re_register;
    @FXML
    private Button register_button_register, back_button_register;
    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    public void initialize(){
        register_button_register.setOnAction(event -> {
            blockButtons(true);
            info_register.setVisible(false);
            createAccount();
        });

        back_button_register.setOnAction(event -> {
            try {
                switchActivity("activity_first_screen");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createAccount(){
        String loginString = String.valueOf(name_register.getText());
        String emailString = String.valueOf(email_register.getText());
        String passwordString= String.valueOf(password_register.getText());
        String passwordReString= String.valueOf(password_re_register.getText());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        Register post = new Register(emailString, passwordString, passwordReString, loginString);
        Call<Register> call = jsonUser.register(post);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(!response.isSuccessful()){
                    Platform.runLater(() -> {
                    info_register.setStyle("-fx-text-fill: #FF0000;");
                    info_register.setVisible(true);
                    info_register.setText("Błąd danych");
                    });
                    blockButtons(false);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Platform.runLater(() -> {
                    info_register.setStyle("-fx-text-fill: #00FF00;");
                    info_register.setVisible(true);
                    info_register.setText("Utworzono konto pomyślnie");
                });
                register_button_register.setVisible(false);
                blockButtons(false);
            }
        });
    }
    private void blockButtons(boolean isLoading){
        double buttonOpacity = isLoading ? 1.0 : 1.0;
        register_button_register.setDisable(isLoading);
        register_button_register.setOpacity(buttonOpacity);
        back_button_register.setDisable(isLoading);
        back_button_register.setOpacity(buttonOpacity);
    }
}