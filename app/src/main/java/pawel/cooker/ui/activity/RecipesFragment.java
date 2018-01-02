package pawel.cooker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pawel.cooker.R;
import pawel.cooker.api.model.BlackListItem;
import pawel.cooker.api.model.Categories;
import pawel.cooker.api.model.CategoryMain;
import pawel.cooker.api.model.Product;
import pawel.cooker.api.model.Recipe;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.BlackItemAdapter;
import pawel.cooker.ui.adapter.CategorySpinnerAdapter;
import pawel.cooker.ui.adapter.MainCategorySpinnerAdapter;
import pawel.cooker.ui.adapter.ProductsAdapter;
import pawel.cooker.ui.adapter.ProductsCheckAdapter;
import pawel.cooker.ui.adapter.RecipeAdapter;
import pawel.cooker.ui.adapter.StateVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesFragment extends Fragment {

    private static RecipesFragment instance;
    private View view;
    private Api api;
    private TagView tagGroup;
    private ApiService apiService;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private ProductsCheckAdapter productsAdapter;
    private List<Recipe> recipesList;
    private EditText editTextSearch;
    private ImageView filterImageView;
    private ImageView addProduct;
    private CheckBox checkBoxBlackList;
    private Spinner mainCategorySpinner;
    private Spinner categorySpinner;
    private AutoCompleteTextView productsAuto;
    private List<CategoryMain> ctmList;
    private List<Categories> ctList;
    public static List<Product> prdList;
    private boolean detailSearchVisible = false;
    private View detailSearch;
    private ImageView closeSearch;
    private String callUrl;
    private String params;
    private int category;
    public static List<Integer> categories;
    public static List<Integer> products;
    private List<Integer> black_products;

    public RecipesFragment() {

    }

    public static RecipesFragment newInstance() {
        instance = new RecipesFragment();
        return instance;
    }

    public static RecipesFragment getInstance(){
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Setup Layout
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppThemeLight);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();

        //param URI
        callUrl=api.BASE_URL+"/api/RecipesParam?";
        params="";
        category=0;
        categories = new ArrayList<>();
        products = new ArrayList<>();

        //items
        black_products = new ArrayList<>();
        view = localInflater .inflate(R.layout.fragment_recipes, container, false);

        //filters UI
        detailSearch = (View) view.findViewById(R.id.search_detail);
        detailSearch.setVisibility(View.GONE);
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        productsAuto = (AutoCompleteTextView) view.findViewById(R.id.autoProducts);
        addProduct = (ImageView) view.findViewById(R.id.add_product_list);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = productsAuto.getText().toString();
                Tag newTag = new Tag(product);
                newTag.radius=10f;
                newTag.isDeletable=true;

                tagGroup.addTag(newTag);

                for (Product item: prdList) {
                    if(item.getNameProduct().toLowerCase().equals(product)){
                        products.add(item.getIdProduct());
                        update_url();
                        break;
                    }
                }
            }
        });
        checkBoxBlackList = (CheckBox) view.findViewById(R.id.checkbox_blacklist);
        checkBoxBlackList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxBlackList.isChecked()){

                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", String.valueOf(LoginActivity.getToken()));
                    Call<List<BlackListItem>> call = apiService.GetBlackItems(LoginActivity.getUser().getIdUser().toString(), map);
                    call.enqueue(new Callback<List<BlackListItem>>() {
                        @Override
                        public void onResponse(Call<List<BlackListItem>> call, Response<List<BlackListItem>> response) {
                            final List<BlackListItem> bll = response.body();
                            if (bll!=null)
                            {
                                for (BlackListItem item:bll) {
                                    black_products.add(item.getIdProduct());
                                }
                                update_url();
                            }
                        }
                        @Override
                        public void onFailure(Call<List<BlackListItem>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    black_products=new ArrayList<Integer>();
                    update_url();
                }
            }
        });
        mainCategorySpinner = (Spinner) view.findViewById(R.id.spinner_main_category);
        mainCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                category = ctmList.get(pos).getIdCategoryMain();
                update_url();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        closeSearch = (ImageView) view.findViewById(R.id.close_search);
        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detailSearchVisible==true){
                    detailSearchVisible=false;
                    detailSearch.setVisibility(View.GONE);
                }
            }
        });
        tagGroup = (TagView) view.findViewById(R.id.products_tag);
        tagGroup.setOnTagDeleteListener(new TagView.OnTagDeleteListener() {
            @Override
            public void onTagDeleted(final TagView view, final Tag tag, final int position) {
                tagGroup.remove(position);
                products.remove(position);
                update_url();
            }
        });

        //main UI
        editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new RecipesFragment.GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        filterImageView = (ImageView) view.findViewById(R.id.filter_button);
        filterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detailSearchVisible==false){
                    detailSearchVisible=true;
                    detailSearch.setVisibility(View.VISIBLE);
                }
            }
        });

        Call<List<Product>> call = apiService.GetProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                prdList= response.body();

                productsAdapter = new ProductsCheckAdapter(getActivity(), R.layout.product_list_check_item, prdList);
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

        Call<List<Recipe>> call4 = apiService.Recipes();
        call4.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipesList = response.body();
                recipeAdapter = new RecipeAdapter(getActivity(), recipesList);
                recyclerView.setAdapter(recipeAdapter);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getActivity(), "Serwer nie działa.", Toast.LENGTH_SHORT).show();
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });

        return view;
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();

        //looping through existing elements
        for (Recipe r : recipesList) {
            //if the existing elements contains the search input
            if (r.getNameRecipe().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filteredRecipes.add(r);
            }
        }
        //calling a method of the adapter class and passing the filtered list
        recipeAdapter.filterList(filteredRecipes);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void update_url()
    {

        callUrl = api.BASE_URL+"/api/RecipesParam?";
        params="";

        if(category!=0){
            callUrl+="category_main="+category+"&";
        }

        for (int item: products) {
            params+="products="+item+"&";
        }

        for (int item: categories) {
            params+="categories="+item+"&";
        }

        for (int item: black_products) {
            params+="black_products="+item+"&";
        }

        callUrl += params;

        Call<List<Recipe>> call = apiService.GetRecipesParam(callUrl);
        Log.i("SENDING", callUrl);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipesList = response.body();
                recipeAdapter.filterList(recipesList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getActivity(), "Serwer nie działa.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
