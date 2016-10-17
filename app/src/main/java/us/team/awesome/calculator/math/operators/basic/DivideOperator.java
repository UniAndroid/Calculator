package us.team.awesome.calculator.math.operators.basic;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class DivideOperator extends CalculationOperator {

    public DivideOperator() {
        super("/");
    }

    @Override
    public CalculationList calculate(int index, CalculationList list) throws DivideByZeroException {
        double firstNumber = getNumberBeforeOperator(index, list);
        double secondNumber = getNumberAfterOperator(index, list);
        double result = firstNumber / secondNumber;
        if(Double.isInfinite(result)) {
            throw new DivideByZeroException("Devide by zero not allowed");
        }
        list.remove(index - 1); // remove firstNumber
        index -= 1; // lower index because operator shift to the left
        list.remove(index + 1); // remove secondNumber
        list.add(index, new CalculationNumber(result)); // add calculated number
        list.remove(index + 1); // remove old operator
        return list;
    }
}
