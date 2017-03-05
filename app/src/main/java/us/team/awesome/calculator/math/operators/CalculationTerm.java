package us.team.awesome.calculator.math.operators;

import java.math.BigDecimal;
import java.util.LinkedList;

import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.math.operators.basic.AddOperator;
import us.team.awesome.calculator.math.operators.basic.DivideOperator;
import us.team.awesome.calculator.math.operators.basic.MultiplyOperator;
import us.team.awesome.calculator.math.operators.basic.SubtractOperator;
import us.team.awesome.calculator.util.DivideByZeroException;

/**
 * Created by Stefan on 21.02.2017.
 */

public class CalculationTerm {

    public CalculationObject currentCalculationObject;

    public CalculationTerm() {
        currentCalculationObject = new CalculationNumber(0);
    }

    public BigDecimal getValue() throws DivideByZeroException {
        return currentCalculationObject.getValue();
    }

    public void addNumber(int num) {
        CalculationNumber number = new CalculationNumber(num);
        currentCalculationObject.addCalculationObject(number);
    }

    public void addNumber(String num) {
        int _num = Integer.parseInt(num);
        this.addNumber(_num);
    }

    public void addAddOperator() {
        AddOperator add = new AddOperator();
        add.setAugend(currentCalculationObject);
        currentCalculationObject = add;
    }

    public void addSubtractOperator() {
        SubtractOperator sub = new SubtractOperator();
        sub.setMinuend(currentCalculationObject);
        currentCalculationObject = sub;
    }

    public void addMultiplyOperator() {
        MultiplyOperator mult = new MultiplyOperator();
        int compareValue = currentCalculationObject.compareTo(mult);
        if (compareValue == 1 || compareValue == 0) {
            mult.setMultiplier(currentCalculationObject);
        } else {
            CalculationObject relevantObject = currentCalculationObject.getRelevantObjectForHigherSequence();
            mult.setMultiplier(relevantObject);
            currentCalculationObject.setRelevantObjectForHigherSequence(mult);
        }
        currentCalculationObject = mult;
    }

    public void addDivideOperator() {
        DivideOperator divide = new DivideOperator();
        int compareValue = currentCalculationObject.compareTo(divide);
        if (compareValue == 1 || compareValue == 0) {
            divide.setDividend(currentCalculationObject);
        } else {
            CalculationObject relevantObject = currentCalculationObject.getRelevantObjectForHigherSequence();
            divide.setDividend(relevantObject);
            currentCalculationObject.setRelevantObjectForHigherSequence(divide);
        }
        currentCalculationObject = divide;
    }
}
