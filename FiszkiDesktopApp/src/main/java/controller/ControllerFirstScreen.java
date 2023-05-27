package controller;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import other.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit.Login;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit.JsonPlaceholderAPI;

public class ControllerFirstScreen {

    @FXML
    private TextField name_first;

    @FXML
    private PasswordField password_first;

    @FXML
    private Button login_button_first, register_button_first, reset_button_first;

    @FXML
    private void switchActivity(String activity) throws IOException {
        App.setRoot(activity);
    }

    private Token token = Token.getInstance();

    public void initialize(){
        login_button_first.setOnAction(event -> {
            checkAccount();
        });

        register_button_first.setOnAction(event -> {
            try {
                switchActivity("activity_register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        reset_button_first.setOnAction(event -> {
            try {
                switchActivity("activity_reset_password");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void checkAccount() {
        //String loginString = String.valueOf(name_first.getText());
        //String passwordString= String.valueOf(password_first.getText());
        String loginString = "dwin333";
        String passwordString= "qwerty123";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Login post = new Login(loginString, passwordString);
        Call<Login> call = jsonPlaceholderAPI.login(post);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.code() == 200){
                    Login post = response.body();
                    String TokenFromRetrofit = post.getToken();
                    System.out.println(TokenFromRetrofit);
                    token.setToken("123");
                    token.setUserName(name_first.getText().toString());
                    try {
                        switchActivity("activity_main_menu");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else System.out.println("Błędne dane");
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
            }
        });
    }
}
