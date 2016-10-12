package us.team.awesome.calculator;

import org.junit.Test;

import us.team.awesome.calculator.math.CalculationList;

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
    public void shouldContainNumberAndOperator() throws Exception {
        CalculationList list = createExampleCalculationList();

        assertEquals(3, list.size());
        assertEquals(19.0, list.getFirst());
        assertEquals("-", list.get(1));
        assertEquals(10.0, list.get(2));
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception {
        CalculationList list = createExampleCalculationList();
        double result = list.calculate();
        assertEquals(9.0, result, 0);
    }

    private CalculationList createExampleCalculationList() {
        CalculationList list = new CalculationList();
        list.addNumber(19.0);
        list.addOperator("-");
        list.addNumber(10.0);
        return list;
    }
}