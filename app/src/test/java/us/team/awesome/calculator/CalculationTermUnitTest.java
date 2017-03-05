package us.team.awesome.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationTerm;
import static org.junit.Assert.assertEquals;
/**
 * Created by Stefan on 05.03.2017.
 */

public class CalculationTermUnitTest {

    @Test
    public void shouldBeEmptyAfterInit() throws Exception {
        CalculationTerm term = new CalculationTerm();
        assertEquals(0, term.currentCalculationObject.getValue().intValue());
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        assertEquals(BigDecimal.valueOf(2), term.getValue());
    }

    @Test
    public void shouldCalculateCorrectly1() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        term.addNumber(2);
        assertEquals(BigDecimal.valueOf(22), term.getValue());
    }

    @Test
    public void shouldCalculateCorrectly2() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        term.addNumber(2);
        term.addAddOperator();
        term.addNumber(3);
        assertEquals(BigDecimal.valueOf(25.0), term.getValue());
    }

    @Test
    public void shouldCalculateCorrectly3() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        term.addNumber(2);
        term.addAddOperator();
        term.addNumber(3);
        term.addSubtractOperator();
        term.addNumber(10);
        term.addNumber(1);
        assertEquals(BigDecimal.valueOf(-76.0), term.getValue());
    }

    @Test
    public void shouldCalculateCorrectly4() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        term.addAddOperator();
        term.addNumber(2);
        term.addAddOperator();
        term.addNumber(3);
        term.addSubtractOperator();
        term.addNumber(10);
        term.addAddOperator();
        term.addNumber(1);
        assertEquals(BigDecimal.valueOf(-2.0), term.getValue());
    }

    @Test
    public void shouldCalculateCorrectly5() throws Exception {
        CalculationTerm term = new CalculationTerm();
        term.addNumber(2);
        term.addAddOperator();
        term.addSubtractOperator();
        term.addNumber(1);
        assertEquals(BigDecimal.valueOf(1.0), term.getValue());
    }
}
