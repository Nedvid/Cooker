package pawel.cooker.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import pawel.cooker.R;
import pawel.cooker.api.model.ElementsDetail;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.ui.adapter.ElementAdapter;


public class RecipeDetail_Elements extends Fragment {
    private View view;

    private ElementAdapter elementAdapter;
    private ArrayList<ElementsDetail> elementsDetail;
    private static String id_recipe;
    private ListView elementList;

    public RecipeDetail_Elements() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recipe_detail__elements, container, false);

        RecipeDetail rd = RecipeDetailFragment.getRecipeDetail();
        elementsDetail = new ArrayList(rd.getElementsDetails());

        elementAdapter = new ElementAdapter(getActivity(), R.layout.list_item, elementsDetail);
        elementList = (ListView) view.findViewById(R.id.list_recipe_items);
        elementList.setItemsCanFocus(false);
        elementList.setAdapter(elementAdapter);


        return view ;
    }
}
