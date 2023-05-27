package controller;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit.JsonPlaceholderAPI;

public class ControllerRegister {
    @FXML
    private TextField name_register, email_register;
    @FXML
    private PasswordField password_register, password_re_register;
    @FXML
    private Button register_button_register, back_button_register;
    @FXML
    private Label create_into_text_register;

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }


    public void initialize(){
        register_button_register.setOnAction(event -> {
            createAccount();
        });

        back_button_register.setOnAction(event -> {
            try {
                register_button_register.setVisible(true);
                register_button_register.setDisable(false);
                create_into_text_register.setVisible(false);
                create_into_text_register.setDisable(true);
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
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Register post = new Register(emailString, passwordString, passwordReString, loginString);
        Call<Register> call = jsonPlaceholderAPI.register(post);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(!response.isSuccessful()){
                    System.out.println("Błąd w rejestracji");
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Utworzono konto pomyślnie");
                register_button_register.setVisible(false);
                register_button_register.setDisable(true);
                create_into_text_register.setVisible(true);
                create_into_text_register.setDisable(false);
            }
        });
    }
}