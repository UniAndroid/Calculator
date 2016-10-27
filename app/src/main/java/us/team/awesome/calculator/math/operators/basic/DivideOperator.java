package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

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
        BigDecimal firstNumber = getNumberBeforeOperator(index, list);
        BigDecimal secondNumber = getNumberAfterOperator(index, list);
        BigDecimal result;
        try{
            result = firstNumber.divide(secondNumber);
        } catch (ArithmeticException e) {
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
