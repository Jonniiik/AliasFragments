package eugene.com.alias;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    private TextView textViewTxtScore, textViewScore, textViewChronometer, textViewWords;
    Button buttonYes, buttonNo;
    ArrayList<Team> myTeams = new ArrayList<>();
    ArrayList<Team> myTeamsSorted = new ArrayList<>(myTeams.size());
    ArrayList<String> myCategories = new ArrayList<>();
    ArrayList<EndOfTurnTerms> EoTTerms = new ArrayList<>();

    int wordsResult, timeResult, score;
    int attempt = 0;
    CountDownTimer timer;
    boolean emptyCategories, play = false, flag = false;

    public PlayFragment() {
        // Required empty public constructor
        Log.e("Проверка", "PlayFragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        Log.e("Проверка", "onCreateView");
        textViewTxtScore = (TextView) view.findViewById(R.id.textViewTxtScore);
        textViewChronometer = (TextView) view.findViewById(R.id.textViewChronometer);
        textViewWords = (TextView) view.findViewById(R.id.textViewWords);
        textViewScore = (TextView) view.findViewById(R.id.textViewScore);
        buttonYes = (Button) view.findViewById(R.id.buttonYes);
        buttonNo = (Button) view.findViewById(R.id.buttonNo);
        Log.e("Проверка", "Проверка");
        Collections.shuffle(myTeams);
        Log.e("Проверка", "Collections");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Log.e("Проверка", "bundle onCreateView");
            myCategories = bundle.getStringArrayList("myCategories");
            wordsResult = bundle.getInt("wordsResult");
            timeResult = bundle.getInt("timeResult");
            myTeams = bundle.getParcelableArrayList("myTeams");
        }
        newAttempt();
        addListenerOnButtonYes();
        addListenerOnButtonNo();
        return view;
    }

    public int turn(int attempt, ArrayList<Team> myTeams) {
        return (attempt % myTeams.size());
    }

    public String getTeam(int attempt, ArrayList<Team> myTeams) {
        Log.e("Проверка", "getTeam");
        return (myTeams.get(turn(attempt, myTeams)).getName());
    }

    public String getReader(int attempt, ArrayList<Team> myTeams) {
        if ((attempt / myTeams.size()) % 2 == 0) {
            Log.e("Проверка", "getReader1");
            return (myTeams.get(turn(attempt, myTeams)).getPlayer1());
        } else {
            Log.e("Проверка", "getReader2");
            return (myTeams.get(turn(attempt, myTeams)).getPlayer2());
        }
    }

    public String getListener(int attempt, ArrayList<Team> myTeams) {
        if ((attempt / myTeams.size()) % 2 == 0) {
            Log.e("Проверка", "getListener");
            return (myTeams.get(turn(attempt, myTeams)).getPlayer2());
        } else {
            Log.e("Проверка", "getListener2");
            return (myTeams.get(turn(attempt, myTeams)).getPlayer1());
        }
    }


    public void addListenerOnButtonYes() {
        textViewTxtScore = (TextView) textViewTxtScore.findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView) textViewWords.findViewById(R.id.textViewWords);
        textViewScore = (TextView) textViewScore.findViewById(R.id.textViewScore);
        buttonYes = (Button) buttonYes.findViewById(R.id.buttonYes);
        Log.e("Проверка", "addListenerOnButtonYes1");
        buttonYes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score++;
                        EoTTerms.add(new EndOfTurnTerms(myCategories.get(0), "+"));
                        myCategories.remove(0);
                        if (myCategories.isEmpty()) {
                            emptyCategories = true;
                            timer.cancel();
                            SeeTerms();
                            Log.e("Проверка", "addListenerOnButtonYes2");
                        } else {
                            textViewWords.setText(myCategories.get(0));
                            textViewScore.setText(String.valueOf(score));
                            Log.e("Проверка", "addListenerOnButtonYes3");
                            if (score == 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
                                textViewScore.setTextColor(getResources().getColor(R.color.blue));
                                Log.e("Проверка", "addListenerOnButtonYes4");
                            } else if (score > 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.green));
                                textViewScore.setTextColor(getResources().getColor(R.color.green));
                                Log.e("Проверка", "addListenerOnButtonYes5");
                            } else {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.red));
                                textViewScore.setTextColor(getResources().getColor(R.color.red));
                                Log.e("Проверка", "addListenerOnButtonYes6");
                            }
                        }
                    }
                }
        );
    }

    public void addListenerOnButtonNo() {
        textViewTxtScore = (TextView) textViewTxtScore.findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView) textViewWords.findViewById(R.id.textViewWords);
        textViewScore = (TextView) textViewScore.findViewById(R.id.textViewScore);
        buttonNo = (Button) buttonNo.findViewById(R.id.buttonNo);
        Log.e("Проверка", "addListenerOnButtonNO1");
        buttonNo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score--;
                        EoTTerms.add(new EndOfTurnTerms(myCategories.get(0), "-"));
                        myCategories.remove(0);
                        Log.e("Проверка", "addListenerOnButtonNO2");
                        if (myCategories.isEmpty()) {
                            emptyCategories = true;
                            timer.cancel();
                            SeeTerms();
                            Log.e("Проверка", "addListenerOnButtonNO3");
                        } else {
                            textViewWords.setText(myCategories.get(0));
                            textViewScore.setText(String.valueOf(score));
                            Log.e("Проверка", "addListenerOnButtonNO4");
                            if (score == 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
                                textViewScore.setTextColor(getResources().getColor(R.color.blue));
                                Log.e("Проверка", "addListenerOnButtonNO5");
                            } else if (score > 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.green));
                                textViewScore.setTextColor(getResources().getColor(R.color.green));
                                Log.e("Проверка", "addListenerOnButtonNO6");
                            } else {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.red));
                                textViewScore.setTextColor(getResources().getColor(R.color.red));
                                Log.e("Проверка", "addListenerOnButtonNO7");
                            }
                        }
                    }

                }
        );
    }

    public void newAttempt() {
        EoTTerms.clear();
        GetReady();
        Log.e("Проверка", "newAttempt");
        textViewScore = (TextView) textViewScore.findViewById(R.id.textViewScore);
        textViewTxtScore = (TextView) textViewTxtScore.findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView) textViewWords.findViewById(R.id.textViewWords);
        textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
        textViewScore.setTextColor(getResources().getColor(R.color.blue));
        textViewScore.setText("0");
        myCategories.remove(0);
        Log.e("Проверка", "newAttempt2");
        if (myCategories.isEmpty()) {
            emptyCategories = true;
            timer.cancel();
            SeeTerms();
            Log.e("Проверка", "newAttempt3");
        } else {
            textViewWords.setText("");
            Log.e("Проверка", "newAttempt4");
        }
    }

    public void GetReady() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Готовы: " + getTeam(attempt, myTeams));
        builder.setMessage("Объясняет: " + getReader(attempt, myTeams) + "\nСлушает: " + getListener(attempt, myTeams));
        Log.e("Проверка", "GetReady");
        builder.setNeutralButton("Начинаем", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("Проверка", "GetReady2");
                timer = new CountDownTimer((timeResult + 1) * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished < 10000 && flag == false) {
                            flag = true;
                            Log.e("Проверка", "GetReady3");
                        }
                        textViewChronometer = (TextView) textViewChronometer.findViewById(R.id.textViewChronometer);
                        textViewChronometer.setText(String.valueOf(millisUntilFinished / 1000));
                        Log.e("Проверка", "GetReady4");
                    }

                    @Override
                    public void onFinish() {
                        flag = false;
                        SeeTerms();
                        Log.e("Проверка", "GetReady5");
                    }
                };
                timer.start();
                textViewWords.setText(myCategories.get(0));
                dialogInterface.cancel();
            }
        });
        android.app.AlertDialog getReady = builder.create();
        getReady.setCancelable(false);
        getReady.setCanceledOnTouchOutside(false);
        getReady.show();
        Log.e("Проверка", "GetReady6");
    }

    public void Highscore() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
        myTeamsSorted.clear();
        if (emptyCategories == true) {
            builder.setTitle("Sorry, Все закончилось\nРекорд");
        } else {
            builder.setTitle("Счёт");
        }
        myTeams.get(turn(attempt, myTeams)).addScore(score);
        score = 0;
        for (Team team : myTeams) {
            myTeamsSorted.add(team);
        }
        Collections.sort(myTeamsSorted, new Comparator<Team>() {
            public int compare(Team t1, Team t2) {
                return t2.getScore() - t1.getScore();
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myTeamsSorted.size(); i++) {
            sb.append(i + 1 + ". " + myTeamsSorted.get(i).getName() + " " + myTeamsSorted.get(i).getScore() + "\n");
        }

        builder.setMessage(sb.toString());
        builder.setNeutralButton("Далее", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (myTeams.get(turn(attempt, myTeams)).getScore() >= wordsResult || emptyCategories == true) {
                    dialogInterface.cancel();
                    announceWinner();
                } else {
                    attempt++;
                    dialogInterface.cancel();
                    newAttempt();
                }
            }
        });
        android.app.AlertDialog highscore = builder.create();
        highscore.setCancelable(false);
        highscore.setCanceledOnTouchOutside(false);
        highscore.show();
    }

    public void HighscoreNew() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
        myTeamsSorted.clear();
        if (emptyCategories == true) {
            builder.setTitle("Sorry, Все закончилось\nСчёт ");
        } else {
            builder.setTitle("Счёт ");
        }

        myTeams.get(turn(attempt, myTeams)).addScore(score);
        score = 0;
        for (Team team : myTeams) {
            myTeamsSorted.add(team);
        }
        Collections.sort(myTeamsSorted, new Comparator<Team>() {
            public int compare(Team t1, Team t2) {
                return t2.getScore() - t1.getScore();
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myTeamsSorted.size(); i++) {
            sb.append(i + 1 + ". " + myTeamsSorted.get(i).getName() + " Счёт: " + myTeamsSorted.get(i).getScore() + "\n");
        }

        builder.setMessage(sb.toString());
        builder.setNeutralButton("Далее", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (myTeams.get(turn(attempt, myTeams)).getScore() >= wordsResult || emptyCategories == true) {
                    dialogInterface.cancel();
                    announceWinner();
                } else {
                    attempt++;
                    dialogInterface.cancel();
                    newAttempt();
                }
            }
        });
        android.app.AlertDialog highscore = builder.create();
        highscore.setCancelable(false);
        highscore.setCanceledOnTouchOutside(false);
        highscore.show();
    }

    public void SeeTerms() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Счёт  " + score);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < EoTTerms.size(); i++) {
            sb.append(EoTTerms.get(i).getTerm() + " " + EoTTerms.get(i).getResult() + "\n");
        }
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Далее", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Highscore();
            }
        });
        final android.app.AlertDialog seeTerms = builder.create();
        seeTerms.setCanceledOnTouchOutside(false);
        seeTerms.setCancelable(false);
        textViewChronometer = (TextView) textViewChronometer.findViewById(R.id.textViewChronometer);
        textViewChronometer.setText("");
        seeTerms.show();
    }

    public void announceWinner() {
        if (play == false) {

        }
        play = true;
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Поздравляю");
        builder.setMessage(myTeamsSorted.get(0).getName() + "\n Сыграй ещё");
        builder.setNegativeButton("Выйти?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Fragment fragment = new PlayFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        builder.setNeutralButton("Заново?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HighscoreNew();
            }
        });
        android.app.AlertDialog winner = builder.create();
        winner.setCanceledOnTouchOutside(false);
        winner.setCancelable(false);
        winner.show();
    }
}
