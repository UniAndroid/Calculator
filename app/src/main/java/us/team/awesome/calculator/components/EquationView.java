package us.team.awesome.calculator.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.team.awesome.calculator.R;
import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.util.EquationMalformedException;

/**
 * Created by JWO on 17.10.2016.
 */

public class EquationView extends LinearLayout{

    private View rootView;

    private CalculationList calculationList;
    private TextView equationTextView, calculationResultTextView;

    public EquationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        this.calculationList = new CalculationList();
        this.updateEquation();
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.component_equation_view, this);

        this.equationTextView = (TextView) rootView.findViewById(R.id.equationTextView);
        this.calculationResultTextView = (TextView) rootView.findViewById(R.id.calculationResultTextView);
    }

    public void addNumber(int number){
        this.calculationList.addNumber(number);
        this.updateEquation();
    }

    public void addNumber(String number){
        this.calculationList.addNumber(number);
        this.updateEquation();
    }

    public void addAddOperator(){
        this.calculationList.addAddOperator();
        this.updateEquation();
    }

    public void addSubtractOperator(){
        this.calculationList.addSubtractOperator();
        this.updateEquation();
    }

    public void addMultiplyOperator(){
        this.calculationList.addMultiplyOperator();
        this.updateEquation();
    }

    public void addDivideOperator(){
        this.calculationList.addDivideOperator();
        this.updateEquation();
    }

    public void addDecimalPoint(){
        this.calculationList.addDecimalPoint();
        this.updateEquation();
    }

    public void clear(){
        this.calculationList = new CalculationList();
        this.updateEquation();
    }

    public void calculate() {
        try {
            double result = this.calculationList.calculateEquation();
            if(result % 1 == 0){
                this.calculationResultTextView.setText("" + (int) this.calculationList.calculateEquation());
            }else {
                this.calculationResultTextView.setText("" + this.calculationList.calculateEquation());
            }
        } catch (EquationMalformedException e) {
            this.calculationResultTextView.setText("ERROR");
            e.printStackTrace();
        }
    }

    public String getCalculationString(){
        return calculationList.toString();
    }

    public CalculationList getCalculationList() {
        return calculationList;
    }

    public void setCalculationList(CalculationList calculationList) {
        this.calculationList = calculationList;
        this.updateEquation();
    }

    private void updateEquation() {
        if(this.calculationList.size() == 0){
            this.equationTextView.setText("0");
        }else {
            this.equationTextView.setText(this.calculationList.toString());
        }
    }
}
