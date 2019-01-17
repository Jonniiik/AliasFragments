package eugene.com.alias;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {

    private Button buttonAddTeam, buttonPlay, buttonEditTeamName;
    private ImageButton buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6;
    private ImageButton buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6;
    private EditText editTextTeamName, editTextPlayer1Name, editTextPlayer2Name;
    private TextView textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6;
    String teamName, playerOneName, playerTwoName = null;
    int wordsResult, timeResult;
    int attempt = 0;//index of the TeamsArray
    int currentSize = 0;
    ArrayList<Team> myTeams = new ArrayList<>();
    ArrayList<String> myCategories = new ArrayList<>();

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        getBundle();


        buttonPlay = (Button) view.findViewById(R.id.buttonPlay);
        buttonAddTeam = (Button) view.findViewById(R.id.buttonAddTeam);
        buttonEditTeamName = (Button) view.findViewById(R.id.buttonEditTeamName);

        buttonEditTeam1 = (ImageButton) view.findViewById(R.id.buttonEditTeam1);
        buttonEditTeam2 = (ImageButton) view.findViewById(R.id.buttonEditTeam2);
        buttonEditTeam3 = (ImageButton) view.findViewById(R.id.buttonEditTeam3);
        buttonEditTeam4 = (ImageButton) view.findViewById(R.id.buttonEditTeam4);
        buttonEditTeam5 = (ImageButton) view.findViewById(R.id.buttonEditTeam5);
        buttonEditTeam6 = (ImageButton) view.findViewById(R.id.buttonEditTeam6);

        buttonDelTeam1 = (ImageButton) view.findViewById(R.id.buttonDelTeam1);
        buttonDelTeam2 = (ImageButton) view.findViewById(R.id.buttonDelTeam2);
        buttonDelTeam3 = (ImageButton) view.findViewById(R.id.buttonDelTeam3);
        buttonDelTeam4 = (ImageButton) view.findViewById(R.id.buttonDelTeam4);
        buttonDelTeam5 = (ImageButton) view.findViewById(R.id.buttonDelTeam5);
        buttonDelTeam6 = (ImageButton) view.findViewById(R.id.buttonDelTeam6);

        editTextTeamName = (EditText) view.findViewById(R.id.editTextTeamName);
        editTextPlayer1Name = (EditText) view.findViewById(R.id.editTextPlayer1Name);
        editTextPlayer2Name = (EditText) view.findViewById(R.id.editTextPlayer2Name);

        textViewTeam1 = (TextView) view.findViewById(R.id.textViewTeam1);
        textViewTeam2 = (TextView) view.findViewById(R.id.textViewTeam2);
        textViewTeam3 = (TextView) view.findViewById(R.id.textViewTeam3);
        textViewTeam4 = (TextView) view.findViewById(R.id.textViewTeam4);
        textViewTeam5 = (TextView) view.findViewById(R.id.textViewTeam5);
        textViewTeam6 = (TextView) view.findViewById(R.id.textViewTeam6);

        buttonPlay.setVisibility(View.GONE);
        buttonEditTeamName.setVisibility(View.GONE);
        buttonEditTeam1.setVisibility(View.GONE);
        buttonEditTeam2.setVisibility(View.GONE);
        buttonEditTeam3.setVisibility(View.GONE);
        buttonEditTeam4.setVisibility(View.GONE);
        buttonEditTeam5.setVisibility(View.GONE);
        buttonEditTeam6.setVisibility(View.GONE);
        buttonDelTeam1.setVisibility(View.GONE);
        buttonDelTeam2.setVisibility(View.GONE);
        buttonDelTeam3.setVisibility(View.GONE);
        buttonDelTeam4.setVisibility(View.GONE);
        buttonDelTeam5.setVisibility(View.GONE);
        buttonDelTeam6.setVisibility(View.GONE);
//        button_play.setOnClickListener(this);

        addListenerOnButtonSign();
        addListenerOnButtonPlay();
        addListenerOnButtonEdit1();
        addListenerOnButtonEdit2();
        addListenerOnButtonEdit3();
        addListenerOnButtonEdit4();
        addListenerOnButtonEdit5();
        addListenerOnButtonEdit6();
        addListenerOnButtonDel1();
        addListenerOnButtonDel2();
        addListenerOnButtonDel3();
        addListenerOnButtonDel4();
        addListenerOnButtonDel5();
        addListenerOnButtonDel6();

        return view;
    }
    public void getBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            wordsResult = bundle.getInt("wordsResult");
            timeResult = bundle.getInt("timeResult");
            myCategories = bundle.getStringArrayList("myCategories");
        }
    }

    public void addListenerOnButtonSign() {
        final TextView[] textViews = {textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6};
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        buttonAddTeam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamName = editTextTeamName.getText().toString();
                        playerOneName = editTextPlayer1Name.getText().toString();
                        playerTwoName = editTextPlayer2Name.getText().toString();
                        if (teamName.length() < 1 || playerOneName.length() < 1 || playerTwoName.length() < 1) {
                            Toast.makeText(getActivity(), "Введи название команды", Toast.LENGTH_SHORT).show();

                        } else if (teamName.length() > 15 || playerOneName.length() > 15 || playerTwoName.length() > 15) {
                            Toast.makeText(getActivity(), "Sorry, максимум 15 символов", Toast.LENGTH_SHORT).show();
                        } else {

                            textViews[attempt].setText(teamName);
                            editButtons[attempt].setVisibility(View.VISIBLE);
                            delButtons[attempt].setVisibility(View.VISIBLE);
                            attempt++;
                            myTeams.add(new Team(teamName, playerOneName, playerTwoName));
                            editTextTeamName.setText("");
                            editTextPlayer1Name.setText("");
                            editTextPlayer2Name.setText("");
                            if (attempt == 6) {
                                buttonAddTeam.setVisibility(View.GONE);
                            }
                            if (attempt == 2) {
                                buttonPlay.setVisibility(View.VISIBLE);
                            }
                            Toast.makeText(getActivity(), "" + myTeams, Toast.LENGTH_SHORT).show();
                            hideKeyboard();
                        }
                    }
                }
        );
    }

    public void edit(final ImageButton button, final TextView textView, final int index) {
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTextTeamName.setText(myTeams.get(index).getName());
                        editTextPlayer1Name.setText(myTeams.get(index).getPlayer1());
                        editTextPlayer2Name.setText(myTeams.get(index).getPlayer2());
                        buttonAddTeam.setVisibility(View.GONE);
                        buttonPlay.setVisibility(View.GONE);
                        for (int i = 0; i < myTeams.size(); i++) {
                            delButtons[i].setVisibility(View.GONE);
                            editButtons[i].setVisibility(View.GONE);
                        }
                        buttonEditTeamName.setVisibility(View.VISIBLE);
                        buttonEditTeamName.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        teamName = editTextTeamName.getText().toString();
                                        playerOneName = editTextPlayer1Name.getText().toString();
                                        playerTwoName = editTextPlayer2Name.getText().toString();
                                        myTeams.get(index).setName(editTextTeamName.getText().toString());
                                        myTeams.get(index).setPlayer1(editTextPlayer1Name.getText().toString());
                                        myTeams.get(index).setPlayer2(editTextPlayer2Name.getText().toString());
                                        textView.setText("");
                                        textView.setText(teamName);
                                        editTextTeamName.setText("");
                                        editTextPlayer1Name.setText("");
                                        editTextPlayer2Name.setText("");
                                        buttonEditTeamName.setVisibility(View.GONE);
                                        if (myTeams.size() >= 2) {
                                            buttonPlay.setVisibility(View.VISIBLE);
                                        }
                                        buttonAddTeam.setVisibility(View.VISIBLE);
                                        for (int i = 0; i < myTeams.size(); i++) {
                                            delButtons[i].setVisibility(View.VISIBLE);
                                            editButtons[i].setVisibility(View.VISIBLE);
                                        }
                                        hideKeyboard();
                                    }
                                }
                        );
                    }
                }
        );
    }

    public void del(final ImageButton button, final TextView textView, final int index) {
        final TextView[] textViews = {textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6};
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTeams.remove(index);
                        textViews[index].setText("");
                        editButtons[index].setVisibility(View.GONE);
                        delButtons[index].setVisibility(View.GONE);
                        attempt--;
                        buttonAddTeam.setVisibility(View.VISIBLE);
                        if (attempt == 1) {
                            buttonPlay.setVisibility(View.GONE);
                        }
                        currentSize = myTeams.size();
                        for (int i = 0; i < attempt + 1; i++) {
                            textViews[i].setText("");
                            editButtons[i].setVisibility(View.GONE);
                            delButtons[i].setVisibility(View.GONE);
                        }
                        for (int i = 0; i < currentSize; i++) {
                            textViews[i].setText(myTeams.get(i).getName());
                            editButtons[i].setVisibility(View.VISIBLE);
                            delButtons[i].setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }

    public void addListenerOnButtonEdit1() {
        edit(buttonEditTeam1, textViewTeam1, 0);
    }

    public void addListenerOnButtonEdit2() {
        edit(buttonEditTeam2, textViewTeam2, 1);
    }

    public void addListenerOnButtonEdit3() {
        edit(buttonEditTeam3, textViewTeam3, 2);
    }

    public void addListenerOnButtonEdit4() {
        edit(buttonEditTeam4, textViewTeam4, 3);
    }

    public void addListenerOnButtonEdit5() {
        edit(buttonEditTeam5, textViewTeam5, 4);
    }

    public void addListenerOnButtonEdit6() {
        edit(buttonEditTeam6, textViewTeam6, 5);
    }

    public void addListenerOnButtonDel1() {
        del(buttonDelTeam1, textViewTeam1, 0);
    }

    public void addListenerOnButtonDel2() {
        del(buttonDelTeam2, textViewTeam2, 1);
    }

    public void addListenerOnButtonDel3() {
        del(buttonDelTeam3, textViewTeam3, 2);
    }

    public void addListenerOnButtonDel4() {
        del(buttonDelTeam4, textViewTeam4, 3);
    }

    public void addListenerOnButtonDel5() {
        del(buttonDelTeam5, textViewTeam5, 4);
    }

    public void addListenerOnButtonDel6() {
        del(buttonDelTeam6, textViewTeam6, 5);
    }

    public void addListenerOnButtonPlay() {
        buttonPlay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment = new PlayFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("myTeams", myTeams);
                        bundle.putStringArrayList("myCategories", myCategories);
                        bundle.putInt("wordsResult", wordsResult);
                        bundle.putInt("timeResult", timeResult);
                        fragment.setArguments(bundle);
                    }
                }
        );
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
