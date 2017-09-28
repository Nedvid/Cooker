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
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.ElementsDetail;

/**
 * Created by pawel on 28.09.2017.
 */

public class BlackItemAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = new ArrayList<String>();

    public BlackItemAdapter(Context context, int layoutResourceId,
                       ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BlackItemAdapter.ItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BlackItemAdapter.ItemHolder();
            holder.textName = (TextView) row.findViewById(R.id.item_name);
            row.setTag(holder);
        } else {
            holder = (BlackItemAdapter.ItemHolder) row.getTag();
        }
        String item = data.get(position);
        holder.textName.setText(item);
        return row;

    }

    static class ItemHolder {
        TextView textName;
    }
}
