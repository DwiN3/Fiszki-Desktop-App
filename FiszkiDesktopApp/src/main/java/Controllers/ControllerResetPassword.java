package Controllers;

import java.io.IOException;

<<<<<<< Updated upstream
import app.App;
import javafx.fxml.FXML;

public class ControllerResetPassword {
    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }

=======
import Retrofit.JsonPlaceholderAPI.JsonUser;
import Retrofit.Models.Register;
import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerResetPassword {
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
                   //Toast.makeText(ActivityPasswordReset.this,"Udało się zmienić hasło1", Toast.LENGTH_SHORT).show();
                }

                if(!response.isSuccessful()){
                    //Toast.makeText(ActivityPasswordReset.this,"Błąd w rejestracji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                if(t.getMessage().equals("timeout")){
                    //Toast.makeText(ActivityPasswordReset.this,"Uruchamianie serwera", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(ActivityPasswordReset.this,"Udało się zmienić hasło2", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
>>>>>>> Stashed changes
}
