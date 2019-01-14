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

    private Button button_sign, button_play, button_apply;
    private ImageButton button_edit1, button_edit2, button_edit3, button_edit4, button_edit5, button_edit6;
    private ImageButton button_del1, button_del2, button_del3, button_del4, button_del5, button_del6;
    private EditText editTextTeam, editTextPlayer1, editTextPlayer2;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6;
    String tmp_team, tmp_player1, tmp_player2 = null;
    int wordsResult, timeResult;
    int attempt = 0;//index of the TeamsArray
    int current_size = 0;
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
        Bundle bundle = this.getArguments();

        button_play = (Button) view.findViewById(R.id.button_play);
        button_sign = (Button) view.findViewById(R.id.button_sign_team);
        button_apply = (Button) view.findViewById(R.id.button_apply);

        button_edit1 = (ImageButton) view.findViewById(R.id.button_edit1);
        button_edit2 = (ImageButton) view.findViewById(R.id.button_edit2);
        button_edit3 = (ImageButton) view.findViewById(R.id.button_edit3);
        button_edit4 = (ImageButton) view.findViewById(R.id.button_edit4);
        button_edit5 = (ImageButton) view.findViewById(R.id.button_edit5);
        button_edit6 = (ImageButton) view.findViewById(R.id.button_edit6);

        button_del1 = (ImageButton) view.findViewById(R.id.button_del1);
        button_del2 = (ImageButton) view.findViewById(R.id.button_del2);
        button_del3 = (ImageButton) view.findViewById(R.id.button_del3);
        button_del4 = (ImageButton) view.findViewById(R.id.button_del4);
        button_del5 = (ImageButton) view.findViewById(R.id.button_del5);
        button_del6 = (ImageButton) view.findViewById(R.id.button_del6);

        editTextTeam = (EditText) view.findViewById(R.id.editText_team_name);
        editTextPlayer1 = (EditText) view.findViewById(R.id.editText_player1_name);
        editTextPlayer2 = (EditText) view.findViewById(R.id.editText_player2_name);

        textView1 = (TextView) view.findViewById(R.id.textView_team1);
        textView2 = (TextView) view.findViewById(R.id.textView_team2);
        textView3 = (TextView) view.findViewById(R.id.textView_team3);
        textView4 = (TextView) view.findViewById(R.id.textView_team4);
        textView5 = (TextView) view.findViewById(R.id.textView_team5);
        textView6 = (TextView) view.findViewById(R.id.textView_team6);

        button_play.setVisibility(View.GONE);
        button_apply.setVisibility(View.GONE);
        button_edit1.setVisibility(View.GONE);
        button_edit2.setVisibility(View.GONE);
        button_edit3.setVisibility(View.GONE);
        button_edit4.setVisibility(View.GONE);
        button_edit5.setVisibility(View.GONE);
        button_edit6.setVisibility(View.GONE);
        button_del1.setVisibility(View.GONE);
        button_del2.setVisibility(View.GONE);
        button_del3.setVisibility(View.GONE);
        button_del4.setVisibility(View.GONE);
        button_del5.setVisibility(View.GONE);
        button_del6.setVisibility(View.GONE);
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
    public void addListenerOnButtonSign() {
        final TextView[] textViews = {textView1, textView2, textView3, textView4, textView5, textView6};
        final ImageButton[] editButtons = {button_edit1, button_edit2, button_edit3, button_edit4, button_edit5, button_edit6};
        final ImageButton[] delButtons = {button_del1, button_del2, button_del3, button_del4, button_del5, button_del6};
        button_sign.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tmp_team = editTextTeam.getText().toString();
                        tmp_player1 = editTextPlayer1.getText().toString();
                        tmp_player2 = editTextPlayer2.getText().toString();
                        if (tmp_team.length() < 1||tmp_player1.length() < 1 || tmp_player2.length() < 1) {
                            Toast.makeText(getActivity(), "Введи название команды", Toast.LENGTH_SHORT).show();

                        } else if (tmp_team.length() > 15 || tmp_player1.length() > 15 || tmp_player2.length() > 15) {
                            Toast.makeText(getActivity(), "Sorry, максимум 15 символов", Toast.LENGTH_SHORT).show();
                        } else {

                            textViews[attempt].setText(tmp_team);
                            editButtons[attempt].setVisibility(View.VISIBLE);
                            delButtons[attempt].setVisibility(View.VISIBLE);
                            attempt++;
                            myTeams.add(new Team(tmp_team, tmp_player1, tmp_player2));
                            editTextTeam.setText("");
                            editTextPlayer1.setText("");
                            editTextPlayer2.setText("");
                            if (attempt == 6) {
                                button_sign.setVisibility(View.GONE);
                            }
                            if (attempt == 2) {
                                button_play.setVisibility(View.VISIBLE);
                            }
                            Toast.makeText(getActivity(),"" + tmp_team + " " + tmp_player1 + " " + tmp_player2, Toast.LENGTH_SHORT).show();
                            hideKeyboard();
                        }
                    }
                }
        );
    }
    public void edit(final ImageButton button, final TextView textView, final int index) {
        final ImageButton[] editButtons = {button_edit1, button_edit2, button_edit3, button_edit4, button_edit5, button_edit6};
        final ImageButton[] delButtons = {button_del1, button_del2, button_del3, button_del4, button_del5, button_del6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTextTeam.setText(myTeams.get(index).getName());
                        editTextPlayer1.setText(myTeams.get(index).getPlayer1());
                        editTextPlayer2.setText(myTeams.get(index).getPlayer2());
                        button_sign.setVisibility(View.GONE);
                        button_play.setVisibility(View.GONE);
                        for (int i = 0; i < myTeams.size(); i++) {
                            delButtons[i].setVisibility(View.GONE);
                            editButtons[i].setVisibility(View.GONE);
                        }
                        button_apply.setVisibility(View.VISIBLE);
                        button_apply.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        tmp_team = editTextTeam.getText().toString();
                                        tmp_player1 = editTextPlayer1.getText().toString();
                                        tmp_player2 = editTextPlayer2.getText().toString();
                                        myTeams.get(index).setName(editTextTeam.getText().toString());
                                        myTeams.get(index).setPlayer1(editTextPlayer1.getText().toString());
                                        myTeams.get(index).setPlayer2(editTextPlayer2.getText().toString());
                                        textView.setText("");
                                        textView.setText(tmp_team);
                                        editTextTeam.setText("");
                                        editTextPlayer1.setText("");
                                        editTextPlayer2.setText("");
                                        button_apply.setVisibility(View.GONE);
                                        if (myTeams.size() >= 2) {
                                            button_play.setVisibility(View.VISIBLE);
                                        }
                                        button_sign.setVisibility(View.VISIBLE);
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
        final TextView[] textViews = {textView1, textView2, textView3, textView4, textView5, textView6};
        final ImageButton[] editButtons = {button_edit1, button_edit2, button_edit3, button_edit4, button_edit5, button_edit6};
        final ImageButton[] delButtons = {button_del1, button_del2, button_del3, button_del4, button_del5, button_del6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTeams.remove(index);
                        textViews[index].setText("");
                        editButtons[index].setVisibility(View.GONE);
                        delButtons[index].setVisibility(View.GONE);
                        attempt--;
                        button_sign.setVisibility(View.VISIBLE);
                        if (attempt == 1) {
                            button_play.setVisibility(View.GONE);
                        }
                        current_size = myTeams.size();
                        for (int i = 0; i < attempt + 1; i++) {
                            textViews[i].setText("");
                            editButtons[i].setVisibility(View.GONE);
                            delButtons[i].setVisibility(View.GONE);
                        }
                        for (int i = 0; i < current_size; i++) {
                            textViews[i].setText(myTeams.get(i).getName());
                            editButtons[i].setVisibility(View.VISIBLE);
                            delButtons[i].setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }
    public void addListenerOnButtonEdit1() {
        edit(button_edit1, textView1, 0);
    }

    public void addListenerOnButtonEdit2() {
        edit(button_edit2, textView2, 1);
    }

    public void addListenerOnButtonEdit3() {
        edit(button_edit3, textView3, 2);
    }

    public void addListenerOnButtonEdit4() {
        edit(button_edit4, textView4, 3);
    }

    public void addListenerOnButtonEdit5() {
        edit(button_edit5, textView5, 4);
    }

    public void addListenerOnButtonEdit6() {
        edit(button_edit6, textView6, 5);
    }

    public void addListenerOnButtonDel1() {
        del(button_del1, textView1, 0);
    }

    public void addListenerOnButtonDel2() {
        del(button_del2, textView2, 1);
    }

    public void addListenerOnButtonDel3() {
        del(button_del3, textView3, 2);
    }

    public void addListenerOnButtonDel4() {
        del(button_del4, textView4, 3);
    }

    public void addListenerOnButtonDel5() {
        del(button_del5, textView5, 4);
    }

    public void addListenerOnButtonDel6() {
        del(button_del6, textView6, 5);
    }

    public void addListenerOnButtonPlay() {
        button_play.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment = new PlayFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Bundle bundle = new Bundle();
                        bundle.putInt("wordsResult",wordsResult);
                        bundle.putInt("timeResult",timeResult);
                        //bundle.putStringArrayList("myTeams",myTeams);
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
