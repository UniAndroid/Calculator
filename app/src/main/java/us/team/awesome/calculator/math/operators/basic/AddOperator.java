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

public class AddOperator extends CalculationObject implements CalculationOperator{

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
    public CalculationObject getLeftCalculationObject() {
        return augend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return addend;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject augend) {
        this.augend = augend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject addend) {
        this.addend = addend;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AddOperator) {
            AddOperator compareOperator = (AddOperator) obj;
            boolean sameAugend = Objects.equals(compareOperator.augend, this.augend);
            boolean sameAddend = Objects.equals(compareOperator.addend, this.addend);
            return sameAddend && sameAugend;
        }
        return super.equals(obj);
    }
}
