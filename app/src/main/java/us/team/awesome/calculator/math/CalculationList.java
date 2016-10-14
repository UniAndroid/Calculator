package us.team.awesome.calculator.math;

import java.util.LinkedList;

import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 12.10.2016.
 */

public class CalculationList extends LinkedList {

    public CalculationList() {
        super();
    }

    public void addNumber(double num) {
        //hier kann man noch validierungen machen
//        add(num);
        add(new CalculationNumber(num));
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

    public double getCalculationResult() throws EquationMalformedException {
        try {
            return calculate(this);
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

        int index = getNextOperatorToCalculate();
        if (index > 0) {
            CalculationOperator operator = (CalculationOperator) list.get(index);
            CalculationList _list = operator.calculate(index, list);
            return calculate(_list);
        } else {
            return ((CalculationNumber) list.getFirst()).getValue();
        }
    }

    private int getNextOperatorToCalculate() {
        //erstmal nur * und /
        int index = getFirstDotOperator();
        if (index != -1) {
            return index;
        }
        index = getFirstLineOperator();
        if (index != -1) {
            return index;
        }
        return -1;
    }

    private int getFirstDotOperator() {
        return getFirstOperatorOf(new TimesOperator(), new DivideOperator());
    }

    private int getFirstLineOperator() {
        return getFirstOperatorOf(new AddOperator(), new SubtractOperator());
    }

    private int getFirstOperatorOf(CalculationOperator operator1, CalculationOperator operator2) {
        int op1Index = indexOf(operator1);
        int op2Index = indexOf(operator2);
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
}
