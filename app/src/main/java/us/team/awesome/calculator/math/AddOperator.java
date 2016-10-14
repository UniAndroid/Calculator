package us.team.awesome.calculator.math;

/**
 * Created by Stefan on 14.10.2016.
 */

class AddOperator extends CalculationOperator{

    AddOperator(){
        super("+");
    }

    @Override
    CalculationList calculate(int index, CalculationList list) {
        double firstNumber = getNumberBeforeOperator(index, list);
        double secondNumber = getNumberAfterOperator(index, list);
        double result = firstNumber + secondNumber;
        list.remove(index - 1); // remove firstNumber
        index -= 1; // lower index because operator shift to the left
        list.remove(index + 1); // remove secondNumber
        list.add(index, new CalculationNumber(result)); // add calculated number
        list.remove(index + 1); // remove old operator
        return list;
    }
}
