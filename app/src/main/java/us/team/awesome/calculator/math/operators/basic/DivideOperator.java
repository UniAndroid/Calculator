package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class DivideOperator extends CalculationObject {

    private CalculationObject dividend;
    private CalculationObject divisor;

    public DivideOperator() {
        super(Constants.CalculationSequence.DIVIDE);
    }

    public void setDividend(CalculationObject dividend) {
        this.dividend = dividend;
    }

    public void setDivisor(CalculationObject divisor) {
        this.divisor = divisor;
    }

    public CalculationObject getDividend() {
        return dividend;
    }

    public CalculationObject getDivisor() {
        return divisor;
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
    public void addCalculationObject(CalculationObject calculationObject) {

    }

    @Override
    public CalculationObject getRelevantObjectForHigherSequence() {
        return getDivisor();
    }

    @Override
    public void setRelevantObjectForHigherSequence(CalculationObject object) {
        setDivisor(object);
    }
}
