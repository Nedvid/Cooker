package pawel.cooker.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.Categories;
import pawel.cooker.api.model.CategoryMain;
import pawel.cooker.api.model.Product;
import pawel.cooker.api.model.Recipe;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.CategorySpinnerAdapter;
import pawel.cooker.ui.adapter.MainCategorySpinnerAdapter;
import pawel.cooker.ui.adapter.ProductsAdapter;
import pawel.cooker.ui.adapter.RecipeAdapter;
import pawel.cooker.ui.adapter.StateVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private View view;
    private Button searchButton;
    private Button clearButton;
    private CheckBox checkBoxBlackList;
    private Spinner mainCategorySpinner;
    private Spinner categorySpinner;
    private AutoCompleteTextView productsAuto;

    private Api api;
    private ApiService apiService;
    private List<CategoryMain> ctmList;
    private List<Categories> ctList;
    private List<Product> prdList;

    public SearchFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //setup light theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppThemeLight);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        view =  localInflater.inflate(R.layout.fragment_search, container, false);

        searchButton = (Button) view.findViewById(R.id.search_button);
        clearButton = (Button) view.findViewById(R.id.clear_button);
        checkBoxBlackList = (CheckBox) view.findViewById(R.id.checkbox_blacklist);
        mainCategorySpinner = (Spinner) view.findViewById(R.id.spinner_main_category);
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        productsAuto = (AutoCompleteTextView) view.findViewById(R.id.autoProducts);
        productsAuto.setThreshold(1);

        api = Api.getInstance();
        apiService = api.getApiService();


        Call<List<Product>> call = apiService.GetProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                prdList= response.body();
                ProductsAdapter productsAdapter = new ProductsAdapter(getContext(), R.layout.product_list_item, prdList);
                productsAuto.setAdapter(productsAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getActivity(), "Serwer nie działa.", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<CategoryMain>> call2 = apiService.GetCategoriesMain();
        call2.enqueue(new Callback<List<CategoryMain>>() {
            @Override
            public void onResponse(Call<List<CategoryMain>> call, Response<List<CategoryMain>> response) {
                ctmList = response.body();
                MainCategorySpinnerAdapter mainCategorySpinnerAdapter= new MainCategorySpinnerAdapter(getActivity(), 0, ctmList);
                mainCategorySpinner.setAdapter(mainCategorySpinnerAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryMain>> call, Throwable t) {
                Toast.makeText(getActivity(), "Serwer nie działa.", Toast.LENGTH_SHORT).show();
            }
        });


        Call<List<Categories>> call3 = apiService.GetCategories();
        call3.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                ctList = response.body();
                CategorySpinnerAdapter categorySpinnerAdapter= new CategorySpinnerAdapter(getActivity(), 0, ctList);
                categorySpinner.setAdapter(categorySpinnerAdapter);
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Toast.makeText(getActivity(), "Serwer nie działa.", Toast.LENGTH_SHORT).show();
            }
        });

        return view ;
    }

}
