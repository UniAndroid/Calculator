package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.math.operators.CalculationOperator;

/**
 * Created by Stefan on 14.10.2016.
 */

public class MultiplyOperator extends CalculationOperator {

    public MultiplyOperator() {
        super("*");
    }

    @Override
    public CalculationList calculate(int index, CalculationList list) {
        BigDecimal firstNumber = getNumberBeforeOperator(index, list);
        BigDecimal secondNumber = getNumberAfterOperator(index, list);
        BigDecimal result = firstNumber.multiply(secondNumber);
        list.remove(index - 1); // remove firstNumber
        index -= 1; // lower index because operator shift to the left
        list.remove(index + 1); // remove secondNumber
        list.add(index, new CalculationNumber(result)); // add calculated number
        list.remove(index + 1); // remove old operator
        return list;
    }
}
