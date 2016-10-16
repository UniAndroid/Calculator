package us.team.awesome.calculator.math;

import java.util.LinkedList;

import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The <code>EquationView</code> handles the form of the equation.
 * It ensures that the structure is correct. E.g. after an operator comes no other operator.
 * For more details in its behavior, look up the different <code>add...</code>-Methods.
 * <p>
 * It also returns the result of the calculated equation.
 * <p>
 * To get a proper formatted String of the equation, call <code>toString</code>.
 */
public class EquationView extends LinkedList {

    private boolean decimalPointAdded = false;

    public EquationView() {
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

    public void addMultiplyOperator() {
        add(new MultiplyOperator());
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

    public double calculateEquation() throws EquationMalformedException {
        try {
            Calculator calculator = new Calculator(this);
            return calculator.getCalculationResult();
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

    public String toString() {
        int size = this.size();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            Object o = this.get(i);
            sb.append(o.toString());
            if (i == size - 1 && decimalPointAdded) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private void setDecimalPointAdded() {
        decimalPointAdded = true;
    }

    private void unsetDecimalPointAdded() {
        decimalPointAdded = false;
    }
}
