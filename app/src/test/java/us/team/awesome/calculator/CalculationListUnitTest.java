package us.team.awesome.calculator;

import org.junit.Test;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;

import static org.junit.Assert.*;

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
        double result = list.getCalculationResult();
        assertEquals(-11, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly1() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(10);
        double result = list.getCalculationResult();
        assertEquals(-1, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly2() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(-10);
        double result = list.getCalculationResult();
        assertEquals(19, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly3() throws Exception {
        CalculationList list = createExampleCalculationList();
        double result = list.getCalculationResult();
        assertEquals(9, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly4() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addAddOperator();
        list.addNumber(-10);
        list.addTimesOperator();
        list.addNumber(2);
        list.addAddOperator();
        list.addNumber(10);
        list.addDivideOperator();
        list.addNumber(5);
        double result = list.getCalculationResult();
        assertEquals(-9, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly5() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(1);
        list.addNumber(2);
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        double result = list.getCalculationResult();
        assertEquals(130, result, 0);
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
        double result = list.getCalculationResult();
        assertEquals(6.25, result, 0);
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
        double result = list.getCalculationResult();
        assertEquals(6.25, result, 0);
    }

    @Test(expected= EquationMalformedException.class)
    public void shouldCalculateFalse() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        double result = list.getCalculationResult();
    }

    @Test(expected= EquationMalformedException.class)
    public void shouldCalculateFalse1() throws Exception {
        CalculationList list = new CalculationList();
        list.addNumber(19);
        list.addDivideOperator();
        list.addNumber(0);
        double result = list.getCalculationResult();
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
        list.addTimesOperator();
        list.addNumber(3);
        return list;
    }
}