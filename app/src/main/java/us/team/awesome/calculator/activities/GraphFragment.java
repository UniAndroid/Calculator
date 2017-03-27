package us.team.awesome.calculator.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import us.team.awesome.calculator.R;
import us.team.awesome.calculator.components.EquationView;
import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.Calculator;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.util.Input;
import us.team.awesome.calculator.util.MathException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    // in this example, a LineChart is initialized from xml

    private LineChart chart;
    private LineDataSet data;

    private EditText editText;
    private Button button;

    public GraphFragment() {
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
    public static GraphFragment newInstance(String param1) {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        this.editText = (EditText) view.findViewById(R.id.functionEquation);
        this.button = (Button) view.findViewById(R.id.equalButton);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Entry>dataList = calculateFunction(editText.getText().toString().toCharArray());
                data = new LineDataSet(dataList, "Test");
                LineData _dat = new LineData(data);
                chart.setData(_dat);
                chart.invalidate();
            }
        });

        this.chart = (LineChart) view.findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, i));
        }
        data = new LineDataSet(entries, "Test");
        LineData lineData = new LineData(data);
        chart.setData(lineData);
        chart.invalidate(); // refresh
        return view;
    }

    private List<Entry> calculateFunction(char[] chars) {
        List<Entry> data = new ArrayList<>();
        if (Input.validFunction(chars)) {
            for (int i = 0; i <= 10; i++) {
                CalculationList calcList = new CalculationList();
                for (char _char : chars) {
                    Log.d("INPUT", ""+_char);
                    switch (_char) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            calcList.addNumber(""+_char);
                            break;
                        }
                        case '+': {
                            calcList.addAddOperator();
                            break;
                        }
                        case '-': {
                            calcList.addSubtractOperator();
                            break;
                        }
                        case '*': {
                            calcList.addMultiplyOperator();
                            break;
                        }
                        case '/': {
                            calcList.addDivideOperator();
                            break;
                        }
                        case '.':
                        case ',': {
                            calcList.addDecimalPoint();
                            break;
                        }
                        case 'N': {
                            if(calcList.getLast() instanceof CalculationNumber){
                                calcList.addMultiplyOperator();
                            }
                            calcList.addNumber(i);
                            break;
                        }
                    }
                    Log.d("CALCLIST", calcList.getLast().toString());
                }
                Log.d("FUNCTION", calcList.toString());
                Calculator calculator = new Calculator(calcList);
                CalculationNumber result = null;
                try {
                    result = calculator.getCalculationResult();
                    data.add(new Entry(i, Float.parseFloat(result.toString())));
                } catch (MathException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
