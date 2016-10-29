package us.team.awesome.calculator.math;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.math.operators.basic.AddOperator;
import us.team.awesome.calculator.math.operators.basic.DivideOperator;
import us.team.awesome.calculator.math.operators.basic.MultiplyOperator;
import us.team.awesome.calculator.math.operators.basic.SubtractOperator;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The Calculator calculates a equation given by an CalculationList.
 * The original CalculationList is not altered.
 */
class Calculator {

    private CalculationList equation;

    Calculator(CalculationList equation) {
        this.equation = (CalculationList) equation.clone();
    }

    CalculationNumber getCalculationResult() throws MathException {
        CalculationNumber result = calculate(equation);
        result.removeZerosFromEnd();
        return result;
    }

    private CalculationNumber calculate(CalculationList list) throws MathException {
        if (list.isEmpty()) {
            return new CalculationNumber(0);
        }

        executeBracketsFirst();

        int index = getNextOperatorToCalculate();
        if (index > 0) {
            CalculationOperator operator = (CalculationOperator) list.get(index);
            list = operator.calculate(index, list);
            return calculate(list);
        } else {
            return ((CalculationNumber) list.getFirst());
        }
    }

    private void executeBracketsFirst() throws MathException {
        while(true) {
            int index = getNextBracket();
            if (index != -1) {
                CalculationList childList = (CalculationList) equation.get(index);
                CalculationNumber result = childList.calculateEquation();
                equation.set(index, result);
            }else{
                break;
            }
        }
    }

    /**
     * gets the index of the next operator that needs to be calculated.
     * Sequence is: * : -> + -
     *
     * @return int representing the index of the next operator to calculate
     */
    private int getNextOperatorToCalculate() {
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

    private int getNextBracket() {
        return equation.indexOf(new CalculationList(false));
    }

    private int getFirstDotOperator() {
        return getFirstOperatorOf(new MultiplyOperator(), new DivideOperator());
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
