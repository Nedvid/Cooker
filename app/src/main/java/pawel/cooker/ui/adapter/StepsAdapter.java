package pawel.cooker.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pawel.cooker.R;


/**
 * Created by pawel on 03.10.2017.
 */

public class StepsAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = new ArrayList<String>();

    public StepsAdapter(Context context, int layoutResourceId,
                       ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StepsAdapter.ItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new StepsAdapter.ItemHolder();
            holder.step = (TextView) row.findViewById(R.id.step);
            holder.instruction = (TextView) row.findViewById(R.id.instruction);
            row.setTag(holder);
        } else {
            holder = (StepsAdapter.ItemHolder) row.getTag();
        }
        String item = data.get(position);
        int step = position +1;
        holder.step.setText("Krok " + step);
        holder.instruction.setText(item);
        return row;

    }

    static class ItemHolder {
        TextView step;
        TextView instruction;
    }
}
