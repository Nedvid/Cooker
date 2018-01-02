package pawel.cooker.ui.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pawel.cooker.R;
import pawel.cooker.api.model.BlackListItem;
import pawel.cooker.api.model.Product;
import pawel.cooker.api.model.User;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.BlackItemAdapter;
import pawel.cooker.ui.adapter.ProductsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlackListFragment extends Fragment {
    private View view;

    private User user;
    private Api api;
    private ApiService apiService;
    private List<BlackListItem> blackListItems;
    private ListView blackItemList;
    private AutoCompleteTextView productsAuto;
    private BlackItemAdapter blackItemAdapter;
    private List<Product> prdList;
    private ImageView addButton;

    public BlackListFragment() {

    }

    public static BlackListFragment newInstance() {
        BlackListFragment fragment = new BlackListFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = LoginActivity.getInstance().user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();

        view = inflater.inflate(R.layout.fragment_black_list, container, false);
        blackItemList = (ListView) view.findViewById(R.id.blackItemList);
        productsAuto = (AutoCompleteTextView) view.findViewById(R.id.auto_text_black_list);
        productsAuto.setThreshold(1);
        addButton = (ImageView) view.findViewById(R.id.add_button_black_list);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(productsAuto.getText().toString()))
                {
                    productsAuto.setError("Pole nie może być puste");
                }else {
                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", String.valueOf(LoginActivity.getToken()));

                    Call<String> call = apiService.PostBlackItems(user.getIdUser(), productsAuto.getText().toString(), map);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body().equals("Added")){
                                Toast.makeText(getActivity(), "Dodano", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });




        Call<List<Product>> call2 = apiService.GetProducts();
        call2.enqueue(new Callback<List<Product>>() {
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

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(LoginActivity.getToken()));

        Call<List<BlackListItem>> call = apiService.GetBlackItems(user.getIdUser().toString(), map);
        call.enqueue(new Callback<List<BlackListItem>>() {
            @Override
            public void onResponse(Call<List<BlackListItem>> call, Response<List<BlackListItem>> response) {
                blackListItems=response.body();
                if (blackListItems!=null)
                {
                    ArrayList<BlackListItem> bid = new ArrayList(blackListItems);
                    blackItemAdapter = new BlackItemAdapter(getActivity(), R.layout.black_list, bid);
                    blackItemList.setItemsCanFocus(false);
                    blackItemList.setAdapter(blackItemAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<BlackListItem>> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });

        return view ;
    }

}
