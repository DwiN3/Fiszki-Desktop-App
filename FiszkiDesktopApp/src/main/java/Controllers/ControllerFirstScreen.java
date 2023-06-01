package Controllers;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Other.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import Retrofit.Models.Login;
import retrofit2.converter.gson.GsonConverterFactory;
import Retrofit.JsonPlaceholderAPI.JsonUser;

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
            blockButtons(true);
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
                //switchActivity("activity_reset_password");
                switchActivity("activity_end_screen");
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
        JsonUser jsonUser = retrofit.create(JsonUser.class);
        Login post = new Login(loginString, passwordString);
        Call<Login> call = jsonUser.login(post);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.code() == 200){
                    Login post = response.body();
                    String TokenFromRetrofit = post.getToken();
                    token.setToken(TokenFromRetrofit);
                    token.setUserName(name_first.getText().toString());
                    blockButtons(false);
                    try {
                        switchActivity("activity_main_menu");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    System.out.println("Błędne dane");
                    blockButtons(false);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println("Serwer nie działa");
            }
        });
    }

    private void blockButtons(boolean isLoading){
        login_button_first.setDisable(isLoading);
        register_button_first.setDisable(isLoading);
        reset_button_first.setDisable(isLoading);
    }
}
