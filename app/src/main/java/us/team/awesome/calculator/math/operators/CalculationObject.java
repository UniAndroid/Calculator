package us.team.awesome.calculator.math.operators;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 21.02.2017.
 */

public abstract class CalculationObject implements Comparable<CalculationObject>{
    private Integer calculationSequence;

    public CalculationObject(int calculationSequence) {
        this.calculationSequence = calculationSequence;
    }

    @Override
    public int compareTo(@NonNull CalculationObject o) {
        return calculationSequence.compareTo(o.calculationSequence);
    }

    public int getCalculationSequence() {
        return calculationSequence;
    }

    public abstract BigDecimal getValue() throws DivideByZeroException;

    public abstract void addCalculationObject(CalculationObject calculationObject);
}
