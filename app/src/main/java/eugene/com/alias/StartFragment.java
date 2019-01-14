package eugene.com.alias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class StartFragment extends Fragment implements View.OnClickListener {


    public StartFragment() {
        // Required empty public constructor
    }

Button buttonStart, buttonSetting;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);

        buttonSetting = (Button) view.findViewById(R.id.buttonSetting);
        buttonSetting.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.buttonStart:
                fragment = new OptionsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.buttonSetting:
                Toast.makeText(getActivity().getApplicationContext(), "Пока не работает", Toast.LENGTH_SHORT).show();
//                fragment = new FragmentBD();
//                FragmentTransaction transactionDB = getFragmentManager().beginTransaction();
//                transactionDB.replace(R.id.fragmentContainer,fragment);
//                transactionDB.addToBackStack(null);
//                transactionDB.commit();
                break;
            default:
                break;

        }
    }
}
