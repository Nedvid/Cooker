package pawel.cooker.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import pawel.cooker.R;
import pawel.cooker.ui.adapter.StepsAdapter;

public class RecipeDetail_Recipe extends Fragment {

    private View view;

    private ArrayList<String> steps;
    private StepsAdapter stepsAdapter;
    private static String id_recipe;
    private ListView stepsList;

    public RecipeDetail_Recipe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recipe_detail__recipe, container, false);

        String instr = RecipeDetailFragment.getRecipeDetail().getInstruction();
        steps = new ArrayList((Arrays.asList(instr.split("\t"))));

        stepsAdapter = new StepsAdapter(getActivity(), R.layout.list_steps_recipe, steps);
        stepsList = (ListView) view.findViewById(R.id.list_steps);
        stepsList.setItemsCanFocus(false);
        stepsList.setAdapter(stepsAdapter);

        return view ;
    }

}
