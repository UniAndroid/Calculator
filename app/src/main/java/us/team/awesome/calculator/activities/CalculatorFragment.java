package us.team.awesome.calculator.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import us.team.awesome.calculator.R;
import us.team.awesome.calculator.components.EquationView;

import static us.team.awesome.calculator.R.id.equationView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private EquationView equationView;
    private Button clearButton;
    private boolean autoCalculate;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d("CALCULATOR FRAGMENT", mParam1);
        }


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        autoCalculate = sharedPref.getBoolean(SettingsActivity.AUTO_CALCULATE, false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        this.equationView = (EquationView) view.findViewById(R.id.equationView);

        this.clearButton = (Button) view.findViewById(R.id.clearButton);
        this.clearButton.setOnClickListener(this);
        Button div = (Button) view.findViewById(R.id.divideButton);
        div.setOnClickListener(this);
        Button multiply = (Button) view.findViewById(R.id.multiplyButton);
        multiply.setOnClickListener(this);
        Button subtract = (Button) view.findViewById(R.id.substractButton);
        subtract.setOnClickListener(this);
        Button add = (Button) view.findViewById(R.id.addButton);
        add.setOnClickListener(this);
        Button oneButton = (Button) view.findViewById(R.id.oneButton);
        oneButton.setOnClickListener(this);
        Button twoButton = (Button) view.findViewById(R.id.twoButton);
        twoButton.setOnClickListener(this);
        Button threeButton = (Button) view.findViewById(R.id.threeButton);
        threeButton.setOnClickListener(this);
        Button fourButton = (Button) view.findViewById(R.id.fourButton);
        fourButton.setOnClickListener(this);
        Button fiveButton = (Button) view.findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(this);
        Button sixButton = (Button) view.findViewById(R.id.sixButton);
        sixButton.setOnClickListener(this);
        Button sevenButton = (Button) view.findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(this);
        Button eightButton = (Button) view.findViewById(R.id.eightButton);
        eightButton.setOnClickListener(this);
        Button nineButton = (Button) view.findViewById(R.id.nineButton);
        nineButton.setOnClickListener(this);
        Button zeroButton = (Button) view.findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(this);
        Button equalButton = (Button) view.findViewById(R.id.equalButton);
        equalButton.setOnClickListener(this);
        Button collonButton  = (Button) view.findViewById(R.id.collonButton );
        collonButton .setOnClickListener(this);
        Button bracketLeftButton = (Button) view.findViewById(R.id.bracketLeftButton);
        bracketLeftButton.setOnClickListener(this);
        Button bracketRightButton = (Button) view.findViewById(R.id.bracketRightButton);
        bracketRightButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        autoCalculate = sharedPref.getBoolean(SettingsActivity.AUTO_CALCULATE, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton: {
                equationView.addAddOperator();
                break;
            }
            case R.id.substractButton: {
                equationView.addSubtractOperator();
                break;
            }
            case R.id.multiplyButton: {
                equationView.addMultiplyOperator();
                break;
            }
            case R.id.divideButton: {
                equationView.addDivideOperator();
                break;
            }
            case R.id.zeroButton: {
                equationView.addNumber(0);
                break;
            }
            case R.id.oneButton: {
                equationView.addNumber(1);
                break;
            }
            case R.id.twoButton: {
                equationView.addNumber(2);
                break;
            }
            case R.id.threeButton: {
                equationView.addNumber(3);
                break;
            }
            case R.id.fourButton: {
                equationView.addNumber(4);
                break;
            }
            case R.id.fiveButton: {
                equationView.addNumber(5);
                break;
            }
            case R.id.sixButton: {
                equationView.addNumber(6);
                break;
            }
            case R.id.sevenButton: {
                equationView.addNumber(7);
                break;
            }
            case R.id.eightButton: {
                equationView.addNumber(8);
                break;
            }
            case R.id.nineButton: {
                equationView.addNumber(9);
                break;
            }
            case R.id.equalButton: {
                equationView.calculate();
                break;
            }
            case R.id.collonButton: {
                equationView.addDecimalPoint();
                break;
            }
            case R.id.bracketLeftButton: {
                equationView.addLeftBracket();
                break;
            }
            case R.id.bracketRightButton: {
                equationView.addRightBracket();
                break;
            }
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
