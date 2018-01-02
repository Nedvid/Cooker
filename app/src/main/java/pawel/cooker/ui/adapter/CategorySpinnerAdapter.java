package pawel.cooker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.Categories;
import pawel.cooker.api.model.CategoryMain;
import pawel.cooker.api.model.Recipe;
import pawel.cooker.ui.activity.RecipesFragment;

/**
 * Created by pawel on 30.10.2017.
 */

public class CategorySpinnerAdapter extends ArrayAdapter<Categories> {
    private Context mContext;
    private ArrayList<Categories> listState;
    private CategorySpinnerAdapter CategorySpinnerAdapter;
    private boolean isFromView = false;

    public CategorySpinnerAdapter(Context context, int resource, List<Categories> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<Categories>) objects;
        this.CategorySpinnerAdapter = this;
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
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.spinner_item_text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.spinner_item_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getNameCategoryRecipe());
        holder.category_id = listState.get(position).getIdCategoryRecipe();

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.mCheckBox.isChecked()){
                    RecipesFragment.categories.add(holder.category_id);
                    RecipesFragment rf = RecipesFragment.getInstance();
                    rf.update_url();
                }else
                {
                    int pos=0;
                    for (int item: RecipesFragment.categories) {
                        if(item == holder.category_id)
                            break;
                        pos++;
                    }

                    RecipesFragment.categories.remove(pos);
                    RecipesFragment rf = RecipesFragment.getInstance();
                    rf.update_url();
                }


            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
        private int category_id;
    }
}
