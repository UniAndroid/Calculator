package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;
import java.util.Objects;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.util.Constants;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class SubtractOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject minuend;
    private CalculationObject subtrahend;

    public SubtractOperator() {
        super(Constants.CalculationSequence.SUBTRACT);
    }

    @Override
    public BigDecimal getValue() throws DivideByZeroException {
        BigDecimal _minuend = minuend.getValue();
        BigDecimal _subtrahend = subtrahend.getValue();
        return _minuend.subtract(_subtrahend);
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return minuend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return subtrahend;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject minuend) {
        this.minuend = minuend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SubtractOperator) {
            SubtractOperator compareOperator = (SubtractOperator) obj;
            boolean sameMinuend = Objects.equals(compareOperator.minuend, this.minuend);
            boolean sameSubtrahend = Objects.equals(compareOperator.subtrahend, this.subtrahend);
            return sameMinuend && sameSubtrahend;
        }
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(minuend != null) {
            sb.append(minuend.toString());
        }
        sb.append("-");
        if(subtrahend != null) {
            sb.append(subtrahend.toString());
        }
        return sb.toString();
    }

    public Object clone() {
        SubtractOperator s;
        s = (SubtractOperator) super.clone();
        if (minuend != null) {
            s.minuend = (CalculationObject) minuend.clone();
        }
        if (subtrahend != null) {
            s.subtrahend = (CalculationObject) subtrahend.clone();
        }
        return s;
    }
}
