package Retrofit.JsonPlaceholderAPI;

import Retrofit.Models.Login;
import Retrofit.Models.Register;
import Retrofit.Models.UserLVL;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Interface representing the JSON User API
 */
public interface JsonUser {
    /**
     * Sends a login request
     * @param login the Login object containing login information
     * @return a Call object containing a Login object
     */
    @POST("users/login")
    Call<Login> login(@Body Login login);

    /**
     * Sends a register request
     * @param register the Register object containing registration information
     * @return a Call object containing a Register object
     */
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);

    /**
     * Sends a password reset request
     * @param register the Register object containing password reset information
     * @return a Call object containing a Register object
     */
    @PUT("users/password-reset")
    Call<Register> resetPassword(@Body Register register);

    /**
     * Retrieves the user's password and login
     * @return a Call object containing a Register object
     */
    @GET("users/password-reminder")
    Call<Register> getPassword(@Body Register register);

    /**
     * Sends a points request to update the user's level
     * @param collectionName the name of the collection
     * @param userLVL        the UserLVL object containing level information
     * @return a Call object containing a UserLVL object
     */
    @PUT("users/users-level/{collectionName}")
    Call<UserLVL> points(@Path("collectionName") String collectionName, @Body UserLVL userLVL);

    /**
     * Retrieves the user's level
     * @return a Call object containing a UserLVL object
     */
    @GET("users/users-level")
    Call<UserLVL> getUserLVL();
}
