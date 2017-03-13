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

public class MultiplyOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject multiplier;
    private CalculationObject multiplicand;

    public MultiplyOperator() {
        super(Constants.CalculationSequence.MULTIPLY);
    }

    public void setMultiplier(CalculationObject multiplier) {
        this.multiplier = multiplier;
    }

    public void setMultiplicand(CalculationObject multiplicand) {
        this.multiplicand = multiplicand;
    }

    public CalculationObject getMultiplier() {
        return multiplier;
    }

    public CalculationObject getMultiplicand() {
        return multiplicand;
    }

    @Override
    public BigDecimal getValue() throws DivideByZeroException {
        BigDecimal _multiplier = multiplier.getValue();
        BigDecimal _multiplicand = multiplicand.getValue();
        return _multiplier.multiply(_multiplicand);
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return multiplier;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return multiplicand;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void setRightCalculationObject(CalculationObject multiplicand) {
        this.multiplicand = multiplicand;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MultiplyOperator) {
            MultiplyOperator compareOperator = (MultiplyOperator) obj;
            boolean sameMultiplier = Objects.equals(compareOperator.multiplier, this.multiplier);
            boolean sameMultiplicand = Objects.equals(compareOperator.multiplicand, this.multiplicand);
            return sameMultiplier && sameMultiplicand;
        }
        return super.equals(obj);
    }

    public Object clone() {
        MultiplyOperator m;
        m = (MultiplyOperator) super.clone();
        if (multiplier != null) {
            m.multiplier = (CalculationObject) multiplier.clone();
        }
        if (multiplicand != null) {
            m.multiplicand = (CalculationObject) multiplicand.clone();
        }
        return m;
    }
}
