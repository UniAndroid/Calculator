package us.team.awesome.calculator.math.operators;

/**
 * Created by Stefan on 11.03.2017.
 */

public interface CalculationOperator{

    CalculationObject getLeftCalculationObject();
    CalculationObject getRightCalculationObject();

    void setLeftCalculationObject(CalculationObject object);
    void setRightCalculationObject(CalculationObject object);
}
