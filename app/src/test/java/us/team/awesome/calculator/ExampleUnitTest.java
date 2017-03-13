package us.team.awesome.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.Calculator;
import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.basic.AddOperator;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void clone_test() throws Exception {
        CalculationList list1 = new CalculationList();
        list1.addNumber(1);
        list1.addDecimalPoint();
        list1.addNumber(2);
        list1.addDecimalPoint();
        list1.addNumber(5);
        list1.addAddOperator();
        list1.addNumber(5);
        CalculationList list2 = (CalculationList) list1.clone();
        CalculationList list3 = list1.deepClone();
    }
}