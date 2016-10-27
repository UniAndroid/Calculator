package us.team.awesome.calculator.math;

import java.math.BigDecimal;

/**
 * Created by Stefan on 14.10.2016.
 */

public class CalculationNumber {

    private BigDecimal value;
    private boolean hasDecimalPoint = false;
    private boolean hasNumberAfterDecimalPoint = false;

    public CalculationNumber(int num) {
        this(new BigDecimal(Double.toString((double) num)));
    }

    public CalculationNumber(double num) {
        this(new BigDecimal(Double.toString(num)));
    }

    public CalculationNumber(BigDecimal num) {
        if (num.doubleValue() % 1 != 0) {
            hasDecimalPoint = true;
            hasNumberAfterDecimalPoint = true;
        }
        this.value = num;
    }

    public BigDecimal getValue() {
        return value;
    }

    /**
     * This method adds <code>attachNum</code> to the <code>value</code> of CalculationNumber.
     * <p>It does not add mathematically. For example if <code>value</code> is 5.0, and
     * <code>attachNum</code> is 2, then the new <code>value</code> will be 52.0.</p>
     * <p>
     * <p>These are some more examples, first argument is the <code>value</code> and second
     * the <code>attachNum</code>:
     * ~ 10.0, 15 -> 1015.0
     * ~ 11.25, 5 -> 11.255</p>
     *
     * @param attachNum the Integer that should be attached to value
     */
    public void attacheNumber(int attachNum) {
        String stringValue = toString();
        if (hasDecimalPoint() && !hasNumberAfterDecimalPoint) {
            hasNumberAfterDecimalPoint = true;
        }
        stringValue += attachNum;
        this.value = new BigDecimal(stringValue);
    }

    public void attachDecimalPoint() {
        hasDecimalPoint = true;
    }

    public boolean hasDecimalPoint() {
        return hasDecimalPoint;
    }

    @Override
    public String toString() {
        if (hasDecimalPoint()) {
            if (hasNumberAfterDecimalPoint) {
                return removeZerosFromEnd(this.value.toString());
            } else {
                return Integer.toString(this.value.intValue()) + ".";
            }
        } else {
            return Integer.toString(this.value.intValue());
        }

    }

    private String removeZerosFromEnd(String number) {
        int indexOfDot = number.indexOf('.');
        int lastIndex;
        int lastZeroIndex;
        while (true) {
            lastIndex = number.length() - 1;
            lastZeroIndex = number.lastIndexOf('0', lastIndex);
            if (lastZeroIndex == lastIndex && zeroNotBehindDot(indexOfDot, lastZeroIndex)) {
                number = number.substring(0, lastIndex);
            } else {
                break;
            }
        }
        return number;
    }

    /**
     * stellt sicher, dass alle nullen vom Ende entfernt werden. Eine null direkt nach dem Punkt
     * wird nicht entfernt. Diese Methode sollte nur aufgerufen werden, wenn
     * <code>hasNumberAfterDecimalPoint</code> = true ist.
     * @param dotIndex index representing the dot
     * @param zeroIndex index representing the zero
     * @return returns true if <code>(zeroIndex - dotIndex) > 1</code>
     */
    private boolean zeroNotBehindDot(int dotIndex, int zeroIndex) {
        // number   2.0     2.010
        // index    012     01234
        int diff = zeroIndex - dotIndex;
        return diff > 1;
    }
}
