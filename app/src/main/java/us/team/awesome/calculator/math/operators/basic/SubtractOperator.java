package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class SubtractOperator extends CalculationObject {

    private CalculationObject minuend;
    private CalculationObject subtrahend;

    public SubtractOperator() {
        super(Constants.CalculationSequence.SUBTRACT);
    }

    public void setMinuend(CalculationObject minuend) {
        this.minuend = minuend;
    }

    public void setSubtrahend(CalculationObject subtrahend) {
        this.subtrahend = subtrahend;
    }

    public CalculationObject getMinuend() {
        return minuend;
    }

    public CalculationObject getSubtrahend() {
        return subtrahend;
    }

    @Override
    public BigDecimal getValue() throws DivideByZeroException {
        BigDecimal _minuend = minuend.getValue();
        BigDecimal _subtrahend = subtrahend.getValue();
        return _minuend.subtract(_subtrahend);
    }

    @Override
    public void addCalculationObject(CalculationObject calculationObject) {
        if(subtrahend != null) {
            subtrahend.addCalculationObject(calculationObject);
        }else{
            setSubtrahend(calculationObject);
        }
    }

    @Override
    public CalculationObject getRelevantObjectForHigherSequence() {
        return getSubtrahend();
    }

    @Override
    public void setRelevantObjectForHigherSequence(CalculationObject object) {
        setSubtrahend(object);
    }
}
