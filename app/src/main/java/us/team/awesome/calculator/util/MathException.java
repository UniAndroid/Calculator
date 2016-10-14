package us.team.awesome.calculator.util;

/**
 * Created by Stefan on 14.10.2016.
 */

public class MathException extends Exception {
    public MathException(String message) {
        super(message);
    }

    public MathException(String message, Exception e) {
        super(message, e);
    }
}
