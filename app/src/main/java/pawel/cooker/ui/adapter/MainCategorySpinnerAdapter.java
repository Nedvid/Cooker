package pawel.cooker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.CategoryMain;

/**
 * Created by pawel on 02.11.2017.
 */

public class MainCategorySpinnerAdapter extends ArrayAdapter<CategoryMain> {
    private Context mContext;
    private ArrayList<CategoryMain> listState;
    private MainCategorySpinnerAdapter MainCategorySpinnerAdapter;
    private boolean isFromView = false;

    public MainCategorySpinnerAdapter(Context context, int resource, List<CategoryMain> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<CategoryMain>) objects;
        this.MainCategorySpinnerAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item_single, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.spinner_item_text_single);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getNameCategoryMain());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
    }
}