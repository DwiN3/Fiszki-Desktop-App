package Retrofit.JsonPlaceholderAPI;

import Retrofit.Models.FlashcardID;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface JsonFlashcardsCollections {
    @GET("flashcards-collections/category/{collectionName}")
    Call<List<List<FlashcardID>>> getCategory(@Path("collectionName") String collectionName);
}
