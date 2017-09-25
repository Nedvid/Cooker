package pawel.cooker.ui.adapter;

/**
 * Created by pawel on 25.09.2017.
 */

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pawel.cooker.R;
import pawel.cooker.api.model.ElementsDetail;

public class ElementAdapter extends ArrayAdapter<ElementsDetail> {

    Context context;
    int layoutResourceId;
    ArrayList<ElementsDetail> data = new ArrayList<ElementsDetail>();

    public ElementAdapter(Context context, int layoutResourceId,
                             ArrayList<ElementsDetail> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ElementsDetailHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ElementsDetailHolder();
            holder.textName = (TextView) row.findViewById(R.id.element_name);
            holder.textLocation = (TextView) row.findViewById(R.id.element_quantity);
            holder.btnEdit = (Button) row.findViewById(R.id.element_add);
            row.setTag(holder);
        } else {
            holder = (ElementsDetailHolder) row.getTag();
        }
        ElementsDetail ElementsDetail = data.get(position);
        holder.textName.setText(ElementsDetail.getNameProduct());
        holder.textLocation.setText(ElementsDetail.getQuantity());
        holder.btnEdit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("Edit Button Clicked", "**********");
                Toast.makeText(context, "Edit button Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });

        return row;

    }

    static class ElementsDetailHolder {
        TextView textName;
        TextView textLocation;
        Button btnEdit;
    }
}
