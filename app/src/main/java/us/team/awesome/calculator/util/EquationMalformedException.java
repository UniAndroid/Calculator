package us.team.awesome.calculator.util;

/**
 * Created by Stefan on 14.10.2016.
 */

public class EquationMalformedException extends MathException {
    public static final String MESSAGE = "Die Gleichung ist nicht korrekt aufgebaut.";

    public EquationMalformedException(String MESSAGE, Exception e) {
        super(MESSAGE, e);
    }

    public EquationMalformedException(Exception e) {
        super(MESSAGE, e);
    }

    public EquationMalformedException() {
        super(MESSAGE);
    }
}
