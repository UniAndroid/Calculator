package us.team.awesome.calculator;

import org.junit.Test;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.Calculator;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stefan on 12.10.2016.
 */

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculationListUnitTest {
    @Test
    public void shouldBeEmptyAfterInit() throws Exception {
        CalculationList list;
        list = new CalculationList();
        assertEquals(0, list.size());
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception {
        CalculationList list = createExampleCalculationListWithDots();
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("-11", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly1() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(10);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("-1", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly2() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(-10);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("19", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly3() throws Exception {
        CalculationList list = createExampleCalculationList();
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("9", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly4() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addAddOperator();
        list.addNumber(-10);
        list.addMultiplyOperator();
        list.addNumber(2);
        list.addAddOperator();
        list.addNumber(10);
        list.addDivideOperator();
        list.addNumber(5);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("-9", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly5() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(1);
        list.addNumber(2);
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("130", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly6() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(1);
        list.addDecimalPoint();
        list.addNumber(2);
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("6.25", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly7() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(1);
        list.addDecimalPoint();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("6.25", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly8() throws Exception {
        CalculationList list = new CalculationList();
        list.addDecimalPoint();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("5.25", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly9() throws Exception {
        CalculationList list = new CalculationList();
        Calculator calculator = new Calculator(list);
        list.addNumber(2);
        list.addMultiplyOperator();
        list.addNumber(5);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("10", result.toString());
        list.addDecimalPoint();
        list.addNumber(1);
        result = calculator.getCalculationResult();
        assertEquals("10.2", result.toString());
    }

    @Test
    public void shouldCalculateCorrectly10() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(0);
        list.addNumber(1);
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("2.01", result.toString());
    }

    @Test
    public void shouldBeFormedCorrect1() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(0);
        list.addNumber(1);
        list.addMultiplyOperator();
        list.addLeftBracket();
        list.addNumber(1);
        list.addAddOperator();
        list.addNumber(2);
        list.addRightBracket();
        assertEquals("2.01*(1+2)", list.toString());
        Calculator calculator = new Calculator(list);
        CalculationNumber result = calculator.getCalculationResult();
        assertEquals("6.03", result.toString());
    }

    @Test
    public void shouldBeFormedCorrect2() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(0);
        list.addNumber(1);
        list.addMultiplyOperator();
// (
        list.addLeftBracket();
        list.addNumber(1);
        list.addAddOperator();
        list.addNumber(2);
        list.addMultiplyOperator();
//      (
        list.addLeftBracket();
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(1);
        list.addRightBracket();
//      )
        list.addRightBracket();
//  )
        assertEquals("2.01*(1+2*(5+1))", list.toString());
        Calculator calculator = new Calculator(list);
        CalculationNumber  result = calculator.getCalculationResult();
        assertEquals("26.13", result.toString());
    }

    @Test
    public void shouldBeFormedCorrect3() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(0);
        list.addNumber(0);
        list.addNumber(1);
        list.addMultiplyOperator();
        list.addNumber(13);

        assertEquals("2.001*13", list.toString());
    }

    @Test(expected= MathException.class)
    public void shouldCalculateFalse() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        Calculator calculator = new Calculator(list);
        calculator.getCalculationResult();
    }

    @Test
    public void shouldCalculateFalseButDisplayRight() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        Calculator calculator = new Calculator(list);
        assertEquals("19-10-", calculator.toString());
    }

    @Test(expected= MathException.class)
    public void shouldCalculateFalse1() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addDivideOperator();
        list.addNumber(0);
        Calculator calculator = new Calculator(list);
        calculator.getCalculationResult();
    }

    private CalculationList createExampleCalculationList() {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        return list;
    }

    private CalculationList createExampleCalculationListWithDots() {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addMultiplyOperator();
        list.addNumber(3);
        return list;
    }
}