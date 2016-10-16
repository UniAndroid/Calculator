package us.team.awesome.calculator.math;

import java.util.Objects;

import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 14.10.2016.
 */

abstract class CalculationOperator {
    private String value;

    CalculationOperator(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    double getNumberBeforeOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index - 1)).getValue();
    }

    double getNumberAfterOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index + 1)).getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CalculationOperator) {
            return Objects.equals(((CalculationOperator) obj).getValue(), value);
        }
        return super.equals(obj);
    }

    abstract CalculationList calculate(int index, CalculationList list) throws MathException;
}
