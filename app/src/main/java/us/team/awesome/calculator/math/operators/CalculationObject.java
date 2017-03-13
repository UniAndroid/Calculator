package us.team.awesome.calculator.math.operators;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 21.02.2017.
 */

public abstract class CalculationObject implements Comparable<CalculationObject>, Cloneable{
    private Integer calculationSequence;

    public CalculationObject(int calculationSequence) {
        this.calculationSequence = calculationSequence;
    }

    @Override
    public int compareTo(@NonNull CalculationObject o) {
        return calculationSequence.compareTo(o.calculationSequence);
    }

    public abstract BigDecimal getValue() throws DivideByZeroException;

    public Object clone(){
        CalculationObject o = null;
        try {
            o = (CalculationObject) super.clone();
            o.calculationSequence = new Integer(this.calculationSequence);
        } catch (CloneNotSupportedException e) {} // Won't happen
        return o;
    }
}
