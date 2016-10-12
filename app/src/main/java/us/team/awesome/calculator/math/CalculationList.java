package us.team.awesome.calculator.math;

import java.util.LinkedList;

/**
 * Created by Stefan on 12.10.2016.
 */

public class CalculationList extends LinkedList {

    public CalculationList(){
        super();
    }

    public void addNumber(double num){
        //hier kann man noch validierungen machen
        add(num);
    }

    public void addOperator(String operator){
        //hier kann man noch validierungen machen
        //wie z.B. nur nach einer Zahl möglich einzufügen
        add(operator);
    }

    public double calculate(){
        if(isEmpty()){
            return 0;
        }
        int index = getNextOperatorToCalculate();
        return -1;
    }

    private int getNextOperatorToCalculate() {
        return 0;
    }
}
