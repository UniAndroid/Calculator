package us.team.awesome.calculator.math;

import java.util.LinkedList;

import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 12.10.2016.
 */

public class CalculationList extends LinkedList {

    private boolean decimalPointAdded = false;

    public CalculationList() {
        super();
    }

    public void addNumber(int num) {
        if (!isEmpty() && getLast() instanceof CalculationNumber) {
            CalculationNumber numInstance = (CalculationNumber) getLast();
            numInstance.attacheNumber(num, decimalPointAdded);
            unsetDecimalPointAdded();
        } else {
            add(new CalculationNumber(num));
        }
    }

    public void addNumber(String num) {
        int _num = Integer.parseInt(num);
        this.addNumber(_num);
    }

    public void addAddOperator() {
        add(new AddOperator());
    }

    public void addSubtractOperator() {
        add(new SubtractOperator());
    }

    public void addTimesOperator() {
        add(new TimesOperator());
    }

    public void addDivideOperator() {
        add(new DivideOperator());
    }

    public void addDecimalPoint() {
        if (!isEmpty() && getLast() instanceof CalculationNumber) {
            CalculationNumber _num = (CalculationNumber) getLast();
            if (_num.hasNoDecimalDigits()) {
                setDecimalPointAdded();
            } else {
                unsetDecimalPointAdded();
            }
        } else {
            addNumber(0);
            setDecimalPointAdded();
        }
    }

    public double getCalculationResult() throws EquationMalformedException {
        try {
            return calculate((CalculationList) this.clone());
        } catch (IndexOutOfBoundsException e) {
            // String aus strings.xml auslesen, dafür ist der context nötigt
            // , kann beim aufruf durch activity mitgegeben werden
            throw new EquationMalformedException("Die Gleichung ist nicht korrekt aufgebaut.", e);
        } catch (DivideByZeroException e) {
            throw new EquationMalformedException("Es darf nicht durch 0 geteilt werden.", e);
        } catch (MathException e) {
            e.printStackTrace();
            throw new EquationMalformedException("Es darf nicht durch 0 geteilt werden.", e);
        }
    }

    private double calculate(CalculationList list) throws MathException {
        if (list.isEmpty()) {
            return 0;
        }

        int index = getNextOperatorToCalculate(list);
        if (index > 0) {
            CalculationOperator operator = (CalculationOperator) list.get(index);
            list = operator.calculate(index, list);
            return calculate(list);
        } else {
            return ((CalculationNumber) list.getFirst()).getValue();
        }
    }

    private int getNextOperatorToCalculate(CalculationList list) {
        //erstmal nur * und /
        int index = getFirstDotOperator(list);
        if (index != -1) {
            return index;
        }
        index = getFirstLineOperator(list);
        if (index != -1) {
            return index;
        }
        return -1;
    }

    private int getFirstDotOperator(CalculationList list) {
        return getFirstOperatorOf(new TimesOperator(), new DivideOperator(), list);
    }

    private int getFirstLineOperator(CalculationList list) {
        return getFirstOperatorOf(new AddOperator(), new SubtractOperator(), list);
    }

    private int getFirstOperatorOf(CalculationOperator operator1, CalculationOperator operator2, CalculationList list) {
        int op1Index = list.indexOf(operator1);
        int op2Index = list.indexOf(operator2);
        if (hasNoOperator(op1Index, op2Index)) {
            return -1;
        } else if (op1BeforeOp2(op1Index, op2Index)) {
            return op1Index;
        } else {
            return op2Index;
        }
    }

    private boolean hasNoOperator(int op1Index, int op2Index) {
        return op1Index == -1 && op2Index == -1;
    }

    private boolean op1BeforeOp2(int op1Index, int op2Index) {
        if (op1Index == -1) {
            return false;
        } else {
            return op2Index == -1 || (op1Index < op2Index);
        }
    }

    private void setDecimalPointAdded() {
        decimalPointAdded = true;
    }

    private void unsetDecimalPointAdded() {
        decimalPointAdded = false;
    }
}
