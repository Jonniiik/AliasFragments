package eugene.com.alias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class OptionsFragment extends Fragment {
    private SeekBar seekBarWord;
    private SeekBar seekBarTime;
    private TextView wordsView;
    private TextView timeView;
    private Switch switchNotWords;
    private Switch switchFine;
    private Boolean Fine = false;
    private Boolean notWords = false;
    Button buttonCategories;
    int wordsResult, timeResult;

    public OptionsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        addSeekBarWordsResult(view);
        addSeekBarTimeResult(view);
        addSwitch(view);
        addButtonCategories(view);

        return view;
    }

    public void addSeekBarWordsResult(View view) {
        seekBarWord = (SeekBar) view.findViewById(R.id.seekBarWord);
        wordsView = (TextView) view.findViewById(R.id.wordsView);

        String wordText = String.valueOf(seekBarWord.getProgress());
        wordsView.setText(wordText);

        seekBarWord.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressWords = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                progressWords = progressV;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String resultTextWords = String.valueOf(progressWords);
                wordsView.setText(resultTextWords);
            }
        });
    }

    public void addSeekBarTimeResult(View view) {
        seekBarTime = (SeekBar) view.findViewById(R.id.seekBarTime);
        timeView = (TextView) view.findViewById(R.id.timeView);

        String timeText = String.valueOf(seekBarTime.getProgress());
        timeView.setText(timeText);

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressTime = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressT, boolean fromUser) {
                progressTime = progressT;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String resultTextTime = String.valueOf(progressTime);
                timeView.setText(resultTextTime);
            }
        });

    }

    public void addSwitch(View view) {
        switchFine = (Switch) view.findViewById(R.id.switchFine);
        switchFine.setChecked(false);
        switchFine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fine = true;
                    Toast.makeText(getActivity().getApplicationContext(), "Fine  " + Fine, Toast.LENGTH_SHORT).show();
                } else {
                    Fine = false;
                    Toast.makeText(getActivity().getApplicationContext(), "Fine  " + Fine, Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchNotWords = (Switch) view.findViewById(R.id.switchNotWords);
        switchNotWords.setChecked(false);
        switchNotWords.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notWords = true;
                    Toast.makeText(getActivity().getApplicationContext(), "notWords  " + notWords, Toast.LENGTH_SHORT).show();
                } else {
                    notWords = false;
                    Toast.makeText(getActivity().getApplicationContext(), "notWords  " + notWords, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addButtonCategories(View view) {
        buttonCategories = (Button) view.findViewById(R.id.buttonCategories);
        buttonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordsResult = Integer.parseInt(wordsView.getText().toString());
                timeResult = Integer.parseInt(timeView.getText().toString());
                Fragment fragment = new CategoriesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putInt("wordsResult", wordsResult);
                bundle.putInt("timeResult", timeResult);
                fragment.setArguments(bundle);

                Toast.makeText(getActivity().getApplicationContext(), "timeResult  " + timeResult + "   wordsResult  " + wordsResult + "  notWords " +
                        " " + notWords + "      Fine " + Fine, Toast.LENGTH_LONG).show();
            }
        });
    }

//    public void onBackPressed() {
//        Intent intent = new Intent(ActivityOptions.this, ActivityTeam.class);
//    }


}
