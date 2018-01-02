package pawel.cooker.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pawel on 13.09.2017.
 */

//SINGLETON

    //to do
    // kalkulator jednostek
    // przefiltrować kategorie, wegańskie wegetariańskie
    

public class Api {

    private static Api instance = null;
    public static final String BASE_URL = "http://10.160.44.73:63818/";
    public static Retrofit retrofit;
    public static ApiService apiService;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }

        return instance;
    }


    private Api() {
        buildRetrofit();
    }

    private void buildRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService()
    {
        return apiService;
    }
}
