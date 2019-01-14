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
    int wordsResult,timeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        SeekBar seekBarWord = (SeekBar) findViewById(R.id.seekBarWord);
//        TextView wordsView = (TextView) findViewById(R.id.wordsView);
//        SeekBar seekBarTime = (SeekBar) findViewById(R.id.seekBarTime);
//        TextView timeView = (TextView) findViewById(R.id.timeView);
////
//        wordsView.setText(seekBarWord.getTextDirection());
//        timeView.setText(seekBarTime.getTextDirection());
//
//        Bundle bundle = new Bundle();
//        bundle.getInt("wordsResult");
//        bundle.getInt("timeResult");
//        Intent intent = new Intent(this, PlayFragment.class);
//        intent.putExtra("wordsResult", wordsResult);
//        intent.putExtra("timeResult", timeResult);
//        startActivity(intent);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new StartFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}