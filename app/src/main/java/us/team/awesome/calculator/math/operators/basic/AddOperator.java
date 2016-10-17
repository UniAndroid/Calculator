package us.team.awesome.calculator.math.operators.basic;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.math.operators.CalculationOperator;

/**
 * Created by Stefan on 14.10.2016.
 */

public class AddOperator extends CalculationOperator {

    public AddOperator(){
        super("+");
    }

    @Override
    public CalculationList calculate(int index, CalculationList list) {
        double firstNumber = getNumberBeforeOperator(index, list);
        double secondNumber = getNumberAfterOperator(index, list);
        double result = firstNumber + secondNumber;
        list.remove(index - 1); // remove firstNumber
        index -= 1; // lower index because operator shift to the left
        list.remove(index + 1); // remove secondNumber
        list.add(index, new CalculationNumber(result)); // add calculated number
        list.remove(index + 1); // remove old operator
        return list;
    }
}
