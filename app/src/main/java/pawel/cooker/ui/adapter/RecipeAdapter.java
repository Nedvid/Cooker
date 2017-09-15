package pawel.cooker.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.common.graph.ElementOrder;

import java.util.List;
import pawel.cooker.R;
import pawel.cooker.api.model.Recipe;
import static com.google.common.io.ByteStreams.copy;
import static java.security.AccessController.getContext;

/**
 * Created by pawel on 13.09.2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder>{

    private Context mContext;
    private List<Recipe> recipeList;
    private Typeface typeface_title;
    private Typeface typeface_subtitle;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView level;
        public TextView person_number;
        public TextView time;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            level = (TextView) view.findViewById(R.id.level);
            time = (TextView) view.findViewById(R.id.time);
            person_number = (TextView) view.findViewById(R.id.person_number);
            thumbnail= (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public RecipeAdapter(Context mContext, List<Recipe> recipeList){
        this.mContext = mContext;
        this.recipeList = recipeList;
        this.typeface_title = Typeface.createFromAsset(mContext.getAssets(), "fonts/Montserrat-Bold.ttf");
        this.typeface_subtitle = Typeface.createFromAsset(mContext.getAssets(), "fonts/Montserrat-Medium.ttf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.name.setText(recipe.getNameRecipe().substring(0,1).toUpperCase() + recipe.getNameRecipe().substring(1));
        holder.level.setText(recipe.getLevel());
        holder.person_number.setText(recipe.getNumberPerson().toString());
        holder.time.setText(recipe.getTime().toString());

        holder.name.setTypeface(typeface_title);
        holder.level.setTypeface(typeface_subtitle);
        holder.person_number.setTypeface(typeface_subtitle);
        holder.time.setTypeface(typeface_subtitle);

        // loading album cover using Glide library
        Glide.with(mContext).load(recipe.getURLPhoto()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }



}