package eugene.com.alias;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    int wordsResult, timeResult;
    ArrayList<String> myCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        openFragment();
    }

    public void openFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new StartFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}