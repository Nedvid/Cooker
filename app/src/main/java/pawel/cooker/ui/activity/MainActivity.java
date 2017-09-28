package pawel.cooker.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import pawel.cooker.R;
import pawel.cooker.api.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationViewEx navigationView;
        navigationView = (BottomNavigationViewEx) findViewById(R.id.navigation_view);
        navigationView.enableAnimation(false);
        navigationView.enableShiftingMode(false);
        navigationView.setTextVisibility(false);
        navigationView.setIconSize(20,20);

        navigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item3:
                                selectedFragment = RecipesFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = ListFragment.newInstance();
                                break;
                            case R.id.action_item5:
                                selectedFragment = BlackListFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

    }
}
