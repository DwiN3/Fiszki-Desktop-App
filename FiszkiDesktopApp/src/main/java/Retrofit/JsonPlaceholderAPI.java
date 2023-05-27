package Retrofit;


import Retrofit.Login;
import Retrofit.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    // USER
    @POST("users/login")
    Call<Retrofit.Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
}
