package retrofit;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {
    // USER
    @POST("users/login")
    Call<retrofit.Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
}
