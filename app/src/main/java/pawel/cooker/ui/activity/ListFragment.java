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
import pawel.cooker.ui.adapter.ElementAdapter;
import pawel.cooker.ui.adapter.ItemAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private View view;

    private User user;
    private Api api;
    private ApiService apiService;
    private List<String> items;
    private ArrayList<String> aitems;
    private ListView itemList;
    private ItemAdapter itemAdapter;

    public ListFragment() {

    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
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

        view = inflater.inflate(R.layout.fragment_list, container, false);

        itemList = (ListView) view.findViewById(R.id.itemList);

        //Retrofit
        api = Api.getInstance();
        apiService = api.getApiService();

        Call<List<String>> call = apiService.ListItems(user.getIdList());
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                items=response.body();
                aitems = new ArrayList(items);
                itemAdapter = new ItemAdapter(getActivity(), R.layout.list, aitems);
                itemList.setItemsCanFocus(false);
                itemList.setAdapter(itemAdapter);
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });

        return view ;
    }

}
