package pawel.cooker.api.service;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import pawel.cooker.api.model.Account;
import pawel.cooker.api.model.BlackListItem;
import pawel.cooker.api.model.Categories;
import pawel.cooker.api.model.CategoryMain;
import pawel.cooker.api.model.CommentsDetail;
import pawel.cooker.api.model.Product;
import pawel.cooker.api.model.Recipe;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.model.Token;
import pawel.cooker.api.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by pawel on 12.09.2017.
 */

public interface ApiService {

    //logowanie
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/token")
    Call<Token> Login (@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    //rejestracja
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/api/account/register/")
    Call<String> Register (@Body Account account);

    //informacje użytkownika
    @Headers({
            "Accept: application/json"
    })
    @GET("/api/Users/login={login}/")
    Call<User> GetUser (@Path("login") String login, @HeaderMap Map<String, String> headers);

    //dodanie komentarza
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/api/Comments/")
    Call<CommentsDetail> PostComment (@Field("Id_Comment") String Id_Comment, @Field("Id_User") String Id_User, @Field("Id_Recipe") String Id_Recipe, @Field("Text") String Text, @Field("Date_Comment") String Date_Comment, @HeaderMap Map<String, String> headers);

    @GET("/api/Products/")
    Call<List<Product>> GetProducts();

    @GET("/api/Categories/")
    Call<List<Categories>> GetCategories();

    @GET("/api/Category_Main/")
    Call<List<CategoryMain>> GetCategoriesMain();

    @GET("/api/Recipes/")
    Call<List<Recipe>> Recipes();

    @GET
    Call<List<Recipe>> GetRecipesParam(@Url String url);

    @GET("/api/Recipes/id={id_recipe}/")
    Call<RecipeDetail> RecipeDetail(@Path("id_recipe") String id_recipe);

    @GET("/api/items/id={id_list}/")
    Call<List<String>> ListItems(@Path("id_list") int id_list);

    @Headers({
            "Accept: application/json"
    })
    @GET("/api/Black_items/id={id_user}")
    Call<List<BlackListItem>> GetBlackItems (@Path("id_user") String id_user, @HeaderMap Map<String, String> headers);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("/api/Black_items/id={id_user}/id_p={id_product}")
    Call<String> DeleteBlackItems (@Path("id_user") int id_user, @Path("id_product") int id_product, @HeaderMap Map<String, String> headers);

    @Headers({
            "Accept: application/json"
    })
    @POST("/api/Black_items/id={id_user}/name={name_product}")
    Call<String> PostBlackItems (@Path("id_user") int id_user, @Path("name_product") String name_product, @HeaderMap Map<String, String> headers);
}
