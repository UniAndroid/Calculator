package us.team.awesome.calculator.math;


import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.math.operators.basic.AddOperator;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.math.operators.basic.DivideOperator;
import us.team.awesome.calculator.math.operators.basic.MultiplyOperator;
import us.team.awesome.calculator.math.operators.basic.SubtractOperator;
import us.team.awesome.calculator.util.EquationMalformedException;

/**
 * Created by Stefan on 21.02.2017.
 */

public class CalculationListParser {

    private CalculationList calculationList;

    public CalculationListParser(CalculationList calculationList) {
        this.calculationList = calculationList.deepClone();
    }

    public CalculationObject parseCalculationList() {
        if (calculationList.isEmpty()) {
            return new CalculationNumber(0);
        }

        parseBrackets();
        parseDotOperators();
        parseLineOperators();

        return (CalculationObject) calculationList.getFirst();
    }

    public void setCalculationList(CalculationList calculationList) {
        this.calculationList = calculationList.deepClone();
    }

    private void parseBrackets() {
        while (true) {
            int index = getIndexOfNextBracket();
            if (index != -1) {
                CalculationList childList = (CalculationList) calculationList.get(index);
                CalculationListParser _parser = new CalculationListParser(childList);
                CalculationObject parsedObject = _parser.parseCalculationList();
                calculationList.set(index, parsedObject);
            } else {
                break;
            }
        }
    }

    private void parseDotOperators() {
        int index = getNextDotOperator();
        while (index >= 0) {
            parseStandardOperator(index);
            index = getNextDotOperator();
        }
    }

    private void parseLineOperators() {
        int index = getNextLineOperator();
        while (index >= 0) {
            parseStandardOperator(index);
            index = getNextLineOperator();
        }
    }

    private void parseStandardOperator(int index) {
        CalculationOperator operator = (CalculationOperator) calculationList.get(index);
        CalculationObject leftObject = getCalculationObjectAt(index - 1);
        CalculationObject rightObject = getCalculationObjectAt(index + 1);
        operator.setLeftCalculationObject(leftObject);
        operator.setRightCalculationObject(rightObject);

        if (rightObject != null) removeUsedCalculationObject(index + 1);
        if (leftObject != null) removeUsedCalculationObject(index - 1);
    }

    private int getNextDotOperator() {
        return getFirstOperatorOf(new MultiplyOperator(), new DivideOperator());
    }

    private int getNextLineOperator() {
        return getFirstOperatorOf(new AddOperator(), new SubtractOperator());
    }

    private int getFirstOperatorOf(CalculationOperator operator1, CalculationOperator operator2) {
        int op1Index = calculationList.indexOf(operator1);
        int op2Index = calculationList.indexOf(operator2);
        if (noOperatorFound(op1Index, op2Index) || listContainsOnlyOneItem()) {
            return -1;
        } else if (op1BeforeOp2(op1Index, op2Index)) {
            return op1Index;
        } else {
            return op2Index;
        }
    }

    private boolean noOperatorFound(int op1Index, int op2Index) {
        return op1Index == -1 && op2Index == -1;
    }

    private boolean listContainsOnlyOneItem() {
        return calculationList.getFirst() == calculationList.getLast();
    }

    private boolean op1BeforeOp2(int op1Index, int op2Index) {
        if (op1Index == -1) {
            return false;
        } else {
            return op2Index == -1 || (op1Index < op2Index);
        }
    }

    private int getIndexOfNextBracket() {
        return calculationList.indexOf(new CalculationList(false));
    }

    private void removeUsedCalculationObject(int index) {
        calculationList.remove(index);
    }

    private CalculationObject getCalculationObjectAt(int index) {
        try {
            return (CalculationObject) calculationList.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(EquationMalformedException.MESSAGE);
            return null;
        }
    }
}