package pawel.cooker.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pawel.cooker.R;
import pawel.cooker.api.model.Product;

/**
 * Created by pawel on 02.11.2017.
 */

public class ProductsAdapter extends ArrayAdapter<Product> {
    private final Context mContext;
    private final List<Product> mProducts;
    private final List<Product> mProducts_All;
    private final List<Product> mProducts_Suggestion;
    private final int mLayoutResourceId;

    public ProductsAdapter(Context context, int resource, List<Product> Products) {
        super(context, resource, Products);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mProducts = new ArrayList<>(Products);
        this.mProducts_All = new ArrayList<>(Products);
        this.mProducts_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mProducts.size();
    }

    public Product getItem(int position) {
        return mProducts.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            Product Product = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.product_item_text);
            name.setText(Product.getNameProduct());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((Product) resultValue).getNameProduct();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mProducts_Suggestion.clear();
                    for (Product Product : mProducts_All) {
                        if (Product.getNameProduct().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mProducts_Suggestion.add(Product);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mProducts_Suggestion;
                    filterResults.count = mProducts_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mProducts.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mProducts.addAll((ArrayList<Product>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Product) {
                            mProducts.add((Product) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mProducts.addAll(mProducts_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}
