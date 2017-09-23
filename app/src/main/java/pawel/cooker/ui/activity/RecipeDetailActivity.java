package pawel.cooker.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import pawel.cooker.R;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.RecipeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailActivity extends AppCompatActivity {

    private Api api;
    private ApiService apiService;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private RecipeDetail recipeDetail;
    private String id_recipe;

    //UI
    private TextView name;
    private TextView level;
    private TextView person_number;
    private TextView person_number_text;
    private TextView time;
    private TextView time_min;
    private ImageView thumbnail;

    //Fonts
    private Typeface typeface_title;
    private Typeface typeface_subtitle;
    private Typeface typeface_small;
    private Typeface typeface_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        this.id_recipe = intent.getStringExtra(RecipeAdapter.EXTRA_MESSAGE);

        //UI
        name = (TextView) findViewById(R.id.name_detail);
        level = (TextView) findViewById(R.id.level_detail);
        time = (TextView) findViewById(R.id.time_detail);
        time_min = (TextView) findViewById(R.id.time_min_detail);
        person_number = (TextView) findViewById(R.id.person_number_detail);
        person_number_text = (TextView) findViewById(R.id.person_number_text_detail);
        thumbnail= (ImageView) findViewById(R.id.thumbnail_detail);

        //fonts
        typeface_title = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        typeface_subtitle = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");
        typeface_small = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        typeface_icon = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();

        Call<RecipeDetail> call = apiService.RecipeDetail(id_recipe);
        call.enqueue(new Callback<RecipeDetail>() {
            @Override
            public void onResponse(Call<RecipeDetail> call, Response<RecipeDetail> response) {
                recipeDetail=response.body();
                setLayout(recipeDetail);
            }
            @Override
            public void onFailure(Call<RecipeDetail> call, Throwable t) {
                Toast.makeText(RecipeDetailActivity.this, "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setLayout(RecipeDetail recipeDetail)
    {
        name.setTypeface(typeface_title);
        level.setTypeface(typeface_subtitle);
        person_number.setTypeface(typeface_subtitle);
        time.setTypeface(typeface_subtitle);
        time_min.setTypeface(typeface_small);
        person_number_text.setTypeface(typeface_small);

        name.setText(recipeDetail.getNameRecipe().substring(0,1).toUpperCase() + recipeDetail.getNameRecipe().substring(1));
        level.setText(recipeDetail.getLevel());
        person_number.setText(recipeDetail.getNumberPerson().toString());
        if(recipeDetail.getNumberPerson()==1) {
            person_number_text.setText("osoba");
        }
        if (recipeDetail.getNumberPerson()==2 || recipeDetail.getNumberPerson()==3||recipeDetail.getNumberPerson()==4){
            person_number_text.setText("osoby");
        }
        if (recipeDetail.getNumberPerson()>4){
            person_number_text.setText("osób");
        }

        time.setText(recipeDetail.getTime().toString());

        // loading album cover using Glide library
        Glide.with(this).load(recipeDetail.getURLPhoto()).into(thumbnail);
    }

}

