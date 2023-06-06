package Retrofit.JsonPlaceholderAPI;


import Retrofit.Models.Login;
import Retrofit.Models.Register;
import Retrofit.Models.UserLVL;
import retrofit2.Call;
import retrofit2.http.*;

public interface JsonUser {
    @POST("users/login")
    Call<Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
    @PUT("users/password-reset")
    Call<Register> resetPassword(@Body Register register);
    @PUT("users/users-level/{collectionName}")
    Call<UserLVL> points(@Path("collectionName") String collectionName, @Body UserLVL userLVL);
    @GET("users/users-level")
    Call<UserLVL> getUserLVL();
}
