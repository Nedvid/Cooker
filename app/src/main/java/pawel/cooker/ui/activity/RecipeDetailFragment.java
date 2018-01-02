package pawel.cooker.ui.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.ArrayList;
import pawel.cooker.R;
import pawel.cooker.api.model.ElementsDetail;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.ElementAdapter;
import pawel.cooker.ui.adapter.StepsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailFragment extends Fragment {

    private View view;

    private Api api;
    private ApiService apiService;

    public static RecipeDetail recipeDetail;

    public static RecipeDetail getRecipeDetail() {
        return recipeDetail;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    private ElementAdapter elementAdapter;
    private ArrayList<ElementsDetail> elementsDetail;
    private ArrayList<String> steps;
    private StepsAdapter stepsAdapter;
    private static String id_recipe;
    private ListView elementList;
    private ListView stepsList;

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

    public RecipeDetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_recipe="1";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        BottomNavigationViewEx navigationView;

        navigationView = (BottomNavigationViewEx) view.findViewById(R.id.navigation_recipe_view);
        navigationView.enableAnimation(true);
        navigationView.enableShiftingMode(false);
        navigationView.setTextVisibility(true);
        navigationView.setIconVisibility(false);
        navigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_recipe_item1:
                                selectedFragment = new RecipeDetail_Recipe();
                                break;
                            case R.id.action_recipe_item2:
                                selectedFragment = new RecipeDetail_Elements();
                                break;
                            case R.id.action_recipe_item3:
                                selectedFragment = new RecipeDetail_Comments();
                                break;
                        }
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_recipe_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });




        id_recipe = this.getArguments().getString("message");

        //UI
        name = (TextView) view.findViewById(R.id.name_detail);
        level = (TextView) view.findViewById(R.id.level_detail);
        time = (TextView) view.findViewById(R.id.time_detail);
        time_min = (TextView) view.findViewById(R.id.time_min_detail);
        person_number = (TextView) view.findViewById(R.id.person_number_detail);
        person_number_text = (TextView) view.findViewById(R.id.person_number_text_detail);
        thumbnail= (ImageView) view.findViewById(R.id.thumbnail_detail);

        //fonts
        typeface_title = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-SemiBold.ttf");
        typeface_subtitle = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Medium.ttf");
        typeface_small = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular.ttf");
        typeface_icon = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");

        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();


        Call<RecipeDetail> call = apiService.RecipeDetail(id_recipe);
        call.enqueue(new Callback<RecipeDetail>() {
            @Override
            public void onResponse(Call<RecipeDetail> call, Response<RecipeDetail> response) {
                setRecipeDetail(response.body());
                setLayout(recipeDetail);
            }
            @Override
            public void onFailure(Call<RecipeDetail> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });
        return view ;
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

        Glide.with(this).load(recipeDetail.getUrLPhoto()).into(thumbnail);
    }

}
