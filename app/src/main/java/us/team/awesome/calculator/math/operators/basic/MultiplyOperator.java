package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class MultiplyOperator extends CalculationObject {

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
    public void addCalculationObject(CalculationObject calculationObject) {
        calculationObject.addCalculationObject(multiplicand);
    }

    @Override
    public CalculationObject getRelevantObjectForHigherSequence() {
        return getMultiplicand();
    }

    @Override
    public void setRelevantObjectForHigherSequence(CalculationObject object) {
        setMultiplicand(object);
    }
}
