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

    public abstract BigDecimal getValue() throws DivideByZeroException;

    public abstract void addCalculationObject(CalculationObject calculationObject);

    /**
     * Liefert das CalculationObject zurück, welches für das einzufügende höherrängige CalculationObject
     * wichtig ist. Beispiel: 1 + 2 ist ein AddOperator mit einem augend von 1 und einem addend von 2.
     * Wird nun ein MultiplyOperator eingefügt, muss der augend 1 und der addend 2 * x heißen.
     * Somit wird die korrekte Berechnung gewährleistet.
     * @return
     */
    public abstract CalculationObject getRelevantObjectForHigherSequence();

    /**
     * siehe getRelevantObjectForHigherSequence nur schreibend und nicht lesend.
     */
    public abstract void setRelevantObjectForHigherSequence(CalculationObject object);
}
