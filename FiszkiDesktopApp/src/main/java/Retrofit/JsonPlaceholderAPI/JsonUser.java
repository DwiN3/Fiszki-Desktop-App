package Retrofit.JsonPlaceholderAPI;


import Retrofit.Models.Login;
import Retrofit.Models.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonUser {
    // USER
    @POST("users/login")
    Call<Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
}
