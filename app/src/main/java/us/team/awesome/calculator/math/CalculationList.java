package us.team.awesome.calculator.math;

import java.util.LinkedList;
import java.util.Objects;

import us.team.awesome.calculator.math.operators.basic.AddOperator;
import us.team.awesome.calculator.math.operators.basic.DivideOperator;
import us.team.awesome.calculator.math.operators.basic.MultiplyOperator;
import us.team.awesome.calculator.math.operators.basic.SubtractOperator;
import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The <code>CalculationList</code> handles the form of the equation.
 * It ensures that the structure is correct. E.g. after an operator comes no other operator.
 * For more details in its behavior, look up the different <code>add...</code>-Methods.
 * <p>
 * It also returns the result of the calculated equation.
 * <p>
 * To get a proper formatted String of the equation, call <code>toString</code>.
 */
public class CalculationList extends LinkedList {

    /**
     * isComplete indicates if the CalculationList has both brackets
     * e.g. if first bracket is set, isComplete is false. Now every add... Method adds to this
     * CalculationList. When the closing bracket is set, isComplete is true and every add... Method
     * adds to the parent CalculationList.
     */
    private boolean isComplete;

    public CalculationList(boolean isComplete) {
        super();
        this.isComplete = isComplete;
    }

    public void addNumber(int num) {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addNumber(num);
        } else {
            if (!isEmpty() && getLast() instanceof CalculationNumber) {
                CalculationNumber numInstance = (CalculationNumber) getLast();
                numInstance.attacheNumber(num);
            } else {
                add(new CalculationNumber(num));
            }
        }
    }

    public void addNumber(String num) {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addNumber(num);
        } else {
            int _num = Integer.parseInt(num);
            this.addNumber(_num);
        }
    }

    public void addAddOperator() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addAddOperator();
        } else {
            add(new AddOperator());
        }
    }

    public void addSubtractOperator() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addSubtractOperator();
        } else {
            add(new SubtractOperator());
        }
    }

    public void addMultiplyOperator() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addMultiplyOperator();
        } else {
            add(new MultiplyOperator());
        }
    }

    public void addDivideOperator() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addDivideOperator();
        } else {
            add(new DivideOperator());
        }
    }

    public void addDecimalPoint() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addDecimalPoint();
        } else {
            if (isEmpty() || !(getLast() instanceof CalculationNumber)) {
                addNumber(0);
            }
            CalculationNumber _num = (CalculationNumber) getLast();
            _num.attachDecimalPoint();
        }
    }

    public void addLeftBracket() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addLeftBracket();
        } else {
            add(new CalculationList(false));
        }
    }

    public void addRightBracket() {
        if (lastIsUncompleteCalculationList()) {
            CalculationList last = (CalculationList) getLast();
            last.addRightBracket();
        } else {
            setIsComplete(true);
        }
    }

    public double calculateEquation() throws MathException {
        try {
            Calculator calculator = new Calculator(this);
            return calculator.getCalculationResult();
        } catch (IndexOutOfBoundsException e) {
            // String aus strings.xml auslesen, dafür ist der context nötigt
            // , kann beim aufruf durch activity mitgegeben werden
            throw new MathException("Die Gleichung ist nicht korrekt aufgebaut.", e);
        } catch (DivideByZeroException e) {
            throw new MathException("Es darf nicht durch 0 geteilt werden.", e);
        }
    }

    public String toString() {
        int size = this.size();
        StringBuilder sb = new StringBuilder(size);
        if (size == 0) {
            sb.append(0);
        } else {
            for (int i = 0; i < size; i++) {
                Object o = this.get(i);
                if (o instanceof CalculationList) {
                    sb.append("(");
                    sb.append(o.toString());
                    sb.append(")");
                } else {
                    sb.append(o.toString());
                }
            }
        }
        return sb.toString();
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CalculationList || super.equals(obj);
    }

    /**
     * This method returns true if the last item in this <code>CalculationList</code> is a
     * <code>CalculationList</code> and <code>isComplete()</code> of same
     * <code>CalculationList</code> returns false
     *
     * @return boolean, representing the state of the last CalculationList, if there is one.
     */
    private boolean lastIsUncompleteCalculationList() {
        if (!isEmpty() && getLast() instanceof CalculationList) {
            CalculationList last = (CalculationList) getLast();
            return !last.isComplete();
        } else {
            return false;
        }
    }
}
