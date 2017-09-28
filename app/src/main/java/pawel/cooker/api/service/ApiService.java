package pawel.cooker.api.service;

import java.util.List;

import pawel.cooker.api.model.Recipe;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pawel on 12.09.2017.
 */

public interface ApiService {

    @GET("/api/users/login={login}/")
    Call<User> DataForUser(@Path("login") String login);

    @GET("/api/Recipes_/")
    Call<List<Recipe>> Recipes();

    @GET("/api/recipes/id={id_recipe}/")
    Call<RecipeDetail> RecipeDetail(@Path("id_recipe") String id_recipe);

    @GET("/api/items/id={id_list}/")
    Call<List<String>> ListItems(@Path("id_list") int id_list);

    @GET("/api/black_items/id={id_user}/")
    Call<List<String>> BlackListItems(@Path("id_user") int id_user);
}
