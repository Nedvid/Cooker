package pawel.cooker.ui.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.ElementsDetail;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.model.User;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.BlackItemAdapter;
import pawel.cooker.ui.adapter.ElementAdapter;
import pawel.cooker.ui.adapter.ItemAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlackListFragment extends Fragment {
    private View view;

    private User user;
    private Api api;
    private ApiService apiService;
    private List<String> items;
    private ArrayList<String> aitems;
    private ListView itemList;
    private BlackItemAdapter blackItemAdapter;

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

        view = inflater.inflate(R.layout.fragment_black_list, container, false);

        itemList = (ListView) view.findViewById(R.id.blackItemList);

        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();

        Call<List<String>> call = apiService.BlackListItems(user.getIdUser());
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                items=response.body();
                aitems = new ArrayList(items);
                blackItemAdapter = new BlackItemAdapter(getActivity(), R.layout.black_list, aitems);
                itemList.setItemsCanFocus(false);
                itemList.setAdapter(blackItemAdapter);
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });

        return view ;
    }

}
