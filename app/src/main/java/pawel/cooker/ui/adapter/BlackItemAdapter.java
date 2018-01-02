package pawel.cooker.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import pawel.cooker.R;
import pawel.cooker.api.model.BlackListItem;
import pawel.cooker.api.model.ElementsDetail;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.activity.BlackListFragment;
import pawel.cooker.ui.activity.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pawel on 28.09.2017.
 */

public class BlackItemAdapter extends ArrayAdapter<BlackListItem> {

    private Api api;
    private ApiService apiService;

    Context context;
    int layoutResourceId;
    ArrayList<BlackListItem> data = new ArrayList<BlackListItem>();

    public BlackItemAdapter(Context context, int layoutResourceId,
                       ArrayList<BlackListItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        api = Api.getInstance();
        apiService = api.getApiService();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BlackItemAdapter.ItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BlackItemAdapter.ItemHolder();
            holder.ProductName = (TextView) row.findViewById(R.id.item_name);
            holder.btnRemove = (Button) row.findViewById(R.id.black_item_remove);
            row.setTag(holder);
        } else {
            holder = (BlackItemAdapter.ItemHolder) row.getTag();
        }
        BlackListItem item = data.get(position);
        holder.ProductName.setText(item.getProductName());
        final int id_product = item.getIdProduct();
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", String.valueOf(LoginActivity.getToken()));
                Call<String> call = apiService.DeleteBlackItems(LoginActivity.getUser().getIdUser(), id_product,  map);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("Removed"))
                        {
                            Toast.makeText(context, "Removed!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Serwer nie jest uruchomiony!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return row;

    }

    static class ItemHolder {
        TextView ProductName;
        int ProductId;
        Button btnRemove;
    }
}
