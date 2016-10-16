package us.team.awesome.calculator.math;

import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The Calculator calculates a equation given by an EquationView.
 * The original EquationView is not altered.
 */
class Calculator {

    private EquationView equation;

    Calculator(EquationView equation) {
        this.equation =(EquationView)equation.clone();
    }

    double getCalculationResult() throws MathException {
        return calculate(equation);
    }

    private double calculate(EquationView list) throws MathException {
        if (list.isEmpty()) {
            return 0;
        }

        int index = getNextOperatorToCalculate();
        if (index > 0) {
            CalculationOperator operator = (CalculationOperator) list.get(index);
            list = operator.calculate(index, list);
            return calculate(list);
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
        int op1Index = equation.indexOf(operator1);
        int op2Index = equation.indexOf(operator2);
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
