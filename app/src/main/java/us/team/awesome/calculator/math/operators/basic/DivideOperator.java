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

public class DivideOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject dividend;
    private CalculationObject divisor;

    public DivideOperator() {
        super(Constants.CalculationSequence.DIVIDE);
    }

    @Override
    public BigDecimal getValue() throws DivideByZeroException {
        BigDecimal _dividend = dividend.getValue();
        BigDecimal _divisor = divisor.getValue();
        try {
            return _dividend.divide(_divisor);
        } catch (ArithmeticException e) {
            throw new DivideByZeroException("Devide by zero not allowed");
        }
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return dividend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return divisor;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject dividend) {
        this.dividend = dividend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DivideOperator) {
            DivideOperator compareOperator = (DivideOperator) obj;
            boolean sameDividend = Objects.equals(compareOperator.dividend, this.dividend);
            boolean sameDivisor = Objects.equals(compareOperator.divisor, this.divisor);
            return sameDividend && sameDivisor;
        }
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(dividend != null) {
            sb.append(dividend.toString());
        }
        sb.append(":");
        if(divisor != null) {
            sb.append(divisor.toString());
        }
        return sb.toString();
    }

    public Object clone() {
        DivideOperator d;
        d = (DivideOperator) super.clone();
        if (dividend != null) {
            d.dividend = (CalculationObject) dividend.clone();
        }
        if (divisor != null) {
            d.divisor = (CalculationObject) divisor.clone();
        }
        return d;
    }
}
