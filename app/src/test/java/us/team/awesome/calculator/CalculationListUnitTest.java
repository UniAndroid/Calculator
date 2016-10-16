package us.team.awesome.calculator;

import org.junit.Test;

import us.team.awesome.calculator.math.EquationView;
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
        EquationView list;
        list = new EquationView();
        assertEquals(0, list.size());
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception {
        EquationView list = createExampleCalculationListWithDots();
        double result = list.calculateEquation();
        assertEquals(-11, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly1() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(10);
        double result = list.calculateEquation();
        assertEquals(-1, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly2() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        list.addNumber(-10);
        double result = list.calculateEquation();
        assertEquals(19, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly3() throws Exception {
        EquationView list = createExampleCalculationList();
        double result = list.calculateEquation();
        assertEquals(9, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly4() throws Exception {
        EquationView list = new EquationView();
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
        double result = list.calculateEquation();
        assertEquals(-9, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly5() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(1);
        list.addNumber(2);
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        double result = list.calculateEquation();
        assertEquals(130, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly6() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(1);
        list.addDecimalPoint();
        list.addNumber(2);
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        double result = list.calculateEquation();
        assertEquals(6.25, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly7() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(1);
        list.addDecimalPoint();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        double result = list.calculateEquation();
        assertEquals(6.25, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly8() throws Exception {
        EquationView list = new EquationView();
        list.addDecimalPoint();
        list.addNumber(2);
        list.addDecimalPoint();
        list.addNumber(5);
        list.addAddOperator();
        list.addNumber(5);
        double result = list.calculateEquation();
        assertEquals(5.25, result, 0);
    }

    @Test
    public void shouldCalculateCorrectly9() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(2);
        list.addMultiplyOperator();
        list.addNumber(5);
        double result = list.calculateEquation();
        assertEquals(10.0, result, 0);
        list.addDecimalPoint();
        list.addNumber(1);
        result = list.calculateEquation();
        assertEquals(10.2, result, 0);
    }

    @Test(expected= EquationMalformedException.class)
    public void shouldCalculateFalse() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addSubtractOperator();
        double result = list.calculateEquation();
    }

    @Test(expected= EquationMalformedException.class)
    public void shouldCalculateFalse1() throws Exception {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addDivideOperator();
        list.addNumber(0);
        double result = list.calculateEquation();
    }

    private EquationView createExampleCalculationList() {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        return list;
    }

    private EquationView createExampleCalculationListWithDots() {
        EquationView list = new EquationView();
        list.addNumber(19);
        list.addSubtractOperator();
        list.addNumber(10);
        list.addMultiplyOperator();
        list.addNumber(3);
        return list;
    }
}