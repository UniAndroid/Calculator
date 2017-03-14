package us.team.awesome.calculator.math.operators.basic;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.util.Constants;
import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class MultiplyOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject multiplier;
    private CalculationObject multiplicand;

    public MultiplyOperator() {
        super(Constants.CalculationSequence.MULTIPLY);
    }

    @Override
    public BigDecimal getValue() throws MathException {
        if(multiplier == null || multiplicand == null) {
            throw new EquationMalformedException();
        }
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(multiplier != null) {
            sb.append(multiplier.toString());
        }
        sb.append("*");
        if(multiplicand != null) {
            sb.append(multiplicand.toString());
        }
        return sb.toString();
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

    @Override
    public void draw(@NonNull Canvas canvas) {

    }
}
