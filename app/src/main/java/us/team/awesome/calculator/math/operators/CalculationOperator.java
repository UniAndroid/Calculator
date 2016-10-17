package us.team.awesome.calculator.math.operators;

import java.util.Objects;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 14.10.2016.
 */

public abstract class CalculationOperator {
    private String value;

    public CalculationOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public double getNumberBeforeOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index - 1)).getValue();
    }

    public double getNumberAfterOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index + 1)).getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CalculationOperator) {
            return Objects.equals(((CalculationOperator) obj).getValue(), value);
        }
        return super.equals(obj);
    }

    public abstract CalculationList calculate(int index, CalculationList list) throws MathException;

    public String toString(){
        return value;
    }
}
