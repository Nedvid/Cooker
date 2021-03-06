package pawel.cooker.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;
import pawel.cooker.R;
import pawel.cooker.api.model.Recipe;
import pawel.cooker.ui.activity.RecipeDetailFragment;

/**
 * Created by pawel on 13.09.2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder>{
    private Context mContext;
    private List<Recipe> recipeList;
    private List<Recipe> mFilteredList;
    private Typeface typeface_title;
    private Typeface typeface_subtitle;
    private Typeface typeface_small;
    private Typeface typeface_icon;
    private View view;
    public static final String EXTRA_MESSAGE = "2";

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView level;
        public TextView person_number;
        public TextView person_number_text;
        public TextView time;
        public TextView time_min;
        public ImageView thumbnail;

        public MyViewHolder(View v){
            super(v);
            view = v;

            name = (TextView) view.findViewById(R.id.name);
            level = (TextView) view.findViewById(R.id.level);
            time = (TextView) view.findViewById(R.id.time);
            time_min = (TextView) view.findViewById(R.id.time_min);
            person_number = (TextView) view.findViewById(R.id.person_number);
            person_number_text = (TextView) view.findViewById(R.id.person_number_text);
            thumbnail= (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public RecipeAdapter(Context mContext, List<Recipe> recipeList){
        this.mContext = mContext;
        this.recipeList = recipeList;
        this.typeface_title = Typeface.createFromAsset(mContext.getAssets(), "fonts/Montserrat-SemiBold.ttf");
        this.typeface_subtitle = Typeface.createFromAsset(mContext.getAssets(), "fonts/Montserrat-Medium.ttf");
        this.typeface_small = Typeface.createFromAsset(mContext.getAssets(), "fonts/Montserrat-Regular.ttf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Recipe recipe = recipeList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                RecipeDetailFragment rdf = new RecipeDetailFragment();
                Bundle bundle = new Bundle();
                String myMessage = recipe.getIdRecipe().toString();
                bundle.putString("message", myMessage );
                rdf.setArguments(bundle);

                transaction.replace(R.id.frame_container, rdf);
                transaction.commit();
            }
        });


        holder.name.setTypeface(typeface_title);
        holder.level.setTypeface(typeface_subtitle);
        holder.person_number.setTypeface(typeface_subtitle);
        holder.time.setTypeface(typeface_subtitle);
        holder.time_min.setTypeface(typeface_small);
        holder.person_number_text.setTypeface(typeface_small);


        holder.name.setText(recipe.getNameRecipe().substring(0,1).toUpperCase() + recipe.getNameRecipe().substring(1));
        holder.level.setText(recipe.getLevel());
        holder.person_number.setText(recipe.getNumberPerson().toString());
        if(recipe.getNumberPerson()==1) {
            holder.person_number_text.setText("osoba");
        }
        if (recipe.getNumberPerson()==2 || recipe.getNumberPerson()==3||recipe.getNumberPerson()==4){
            holder.person_number_text.setText("osoby");
        }
        if (recipe.getNumberPerson()>4){
            holder.person_number_text.setText("osób");
        }

        holder.time.setText(recipe.getTime().toString());

        // loading album cover using Glide library
        Glide.with(mContext).load(recipe.getUrLPhoto()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void filterList(List<Recipe> filteredRecipes) {
        this.recipeList = filteredRecipes;
        notifyDataSetChanged();
    }
}