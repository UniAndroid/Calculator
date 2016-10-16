package us.team.awesome.calculator.math;

import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

class DivideOperator extends CalculationOperator {

    DivideOperator() {
        super("/");
    }

    @Override
    EquationView calculate(int index, EquationView list) throws DivideByZeroException {
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
