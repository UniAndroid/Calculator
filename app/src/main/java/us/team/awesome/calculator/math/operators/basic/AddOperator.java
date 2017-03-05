package us.team.awesome.calculator.math.operators.basic;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class AddOperator extends CalculationObject {

    private CalculationObject augend;
    private CalculationObject addend;

    public AddOperator() {
        super(Constants.CalculationSequence.ADD);
    }

    public void setAugend(CalculationObject augend) {
        this.augend = augend;
    }

    public void setAddend(CalculationObject addend) {
        this.addend = addend;
    }

    public CalculationObject getAugend() {
        return augend;
    }

    public CalculationObject getAddend() {
        return addend;
    }

    @Override
    public BigDecimal getValue() throws DivideByZeroException {
        BigDecimal _augend = augend.getValue();
        BigDecimal _addend = addend.getValue();
        return _augend.add(_addend);
    }

    @Override
    public void addCalculationObject(CalculationObject calculationObject) {
        if(addend != null) {
            addend.addCalculationObject(calculationObject);
        }else{
            setAddend(calculationObject);
        }
    }

    @Override
    public CalculationObject getRelevantObjectForHigherSequence() {
        return getAddend();
    }

    @Override
    public void setRelevantObjectForHigherSequence(CalculationObject object) {
        setAddend(object);
    }
}
