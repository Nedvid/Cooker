package pawel.cooker.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pawel.cooker.R;
import pawel.cooker.api.model.CommentsDetail;
import pawel.cooker.api.model.RecipeDetail;
import pawel.cooker.api.model.User;
import pawel.cooker.api.service.Api;
import pawel.cooker.api.service.ApiService;
import pawel.cooker.ui.adapter.CommentsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeDetail_Comments extends Fragment {

    private View view;
    private CommentsAdapter commentsAdapter;
    private ArrayList<CommentsDetail> commentsDetail;
    private ListView commentList;
    private Button addComment;
    private EditText textComment;

    private Api api;
    private ApiService apiService;

    public RecipeDetail_Comments() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = Api.getInstance();
        apiService = api.getApiService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recipe_detail__comments, container, false);

        Call<RecipeDetail> call = apiService.RecipeDetail(RecipeDetailFragment.getRecipeDetail().getIdRecipe().toString());
        call.enqueue(new Callback<RecipeDetail>() {
            @Override
            public void onResponse(Call<RecipeDetail> call, Response<RecipeDetail> response) {

                if(response.body()==null){
                    Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
                }else{
                    commentsDetail = new ArrayList(response.body().getCommentsDetails());
                    commentsAdapter= new CommentsAdapter(getActivity(), R.layout.list_comment, commentsDetail);
                    commentList = (ListView) view.findViewById(R.id.list_recipe_comments);
                    commentList.setItemsCanFocus(false);
                    commentList.setAdapter(commentsAdapter);
                }

            }
            @Override
            public void onFailure(Call<RecipeDetail> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd.", Toast.LENGTH_SHORT).show();
            }
        });

        textComment = (EditText) view.findViewById(R.id.comment_edittext);
        addComment = (Button) view.findViewById(R.id.add_comment_button);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(textComment.getText().length()<10){
                    textComment.setError("Komentarz jest za krótki");
                    textComment.requestFocus();
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", String.valueOf(LoginActivity.getToken()));

                    User user = LoginActivity.getUser();
                    Date date = new Date();
                    CommentsDetail cd = new CommentsDetail();

                    Call<CommentsDetail> call = apiService.PostComment("0", user.getIdUser().toString(), RecipeDetailFragment.recipeDetail.getIdRecipe().toString(), textComment.getText().toString(), "2017-10-15 20:45", map );
                    call.enqueue(new Callback<CommentsDetail>() {
                        @Override
                        public void onResponse(Call<CommentsDetail> call, Response<CommentsDetail> response) {
                            if(response.body()==null){
                                Log.d("RESPONSE", String.valueOf(response.errorBody()));
                                Toast.makeText(view.getContext(), "Błąd!.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(view.getContext(), "Dodano!.", Toast.LENGTH_SHORT).show();

                                refresh();
                            }

                        }

                        @Override
                        public void onFailure(Call<CommentsDetail> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Serwer nie jest uruchomiony.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return view ;
    }

    public void refresh(){

    }
}
