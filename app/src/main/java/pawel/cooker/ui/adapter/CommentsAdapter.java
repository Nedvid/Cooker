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
import pawel.cooker.api.model.CommentsDetail;

public class CommentsAdapter extends ArrayAdapter<CommentsDetail> {

    Context context;
    int layoutResourceId;
    ArrayList<CommentsDetail> data = new ArrayList<CommentsDetail>();

    public CommentsAdapter(Context context, int layoutResourceId,
                          ArrayList<CommentsDetail> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CommentsDetailHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new CommentsDetailHolder();
            holder.author_comment = (TextView) row.findViewById(R.id.author_comment);
            holder.date_comment = (TextView) row.findViewById(R.id.date_comment);
            holder.text_comment = (TextView) row.findViewById(R.id.text_comment);
            row.setTag(holder);
        } else {
            holder = (CommentsDetailHolder) row.getTag();
        }

        CommentsDetail commentsDetail = data.get(position);
        holder.author_comment.setText(commentsDetail.getNameUser());
        holder.text_comment.setText(commentsDetail.getText());
        holder.date_comment.setText(commentsDetail.getDateComment());
        return row;

    }

    static class CommentsDetailHolder {
        TextView author_comment;
        TextView date_comment;
        TextView text_comment;
    }
}
