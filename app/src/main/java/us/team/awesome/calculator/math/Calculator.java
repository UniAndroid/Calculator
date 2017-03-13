package us.team.awesome.calculator.math;

import java.math.BigDecimal;
import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The Calculator calculates a equation given by an CalculationList.
 * The original CalculationList is not altered.
 */
public class Calculator {

    private CalculationList equation;
    private CalculationListParser parser;
    private CalculationObject term;

    public Calculator(CalculationList equation) {
        this.equation = equation;
        this.parser = new CalculationListParser(equation);
    }

    public CalculationNumber getCalculationResult() throws MathException {
        parser.setCalculationList(equation);
        term = parser.parseCalculationList();

        BigDecimal result = term.getValue();
        CalculationNumber num = new CalculationNumber(result);
        num.removeZerosFromEnd();
        return num;
    }

    public void setEquation(CalculationList calculationList) {
        this.equation = calculationList;
    }
}
