package us.team.awesome.calculator.util;

/**
 * Created by Stefan on 14.10.2016.
 */

public class EquationMalformedException extends Exception {
    public EquationMalformedException(String message, Exception e) {
        super(message, e);
    }
}
