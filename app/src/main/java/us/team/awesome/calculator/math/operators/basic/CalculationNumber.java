package us.team.awesome.calculator.math.operators.basic;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.util.Constants;

/**
 * Created by Stefan on 14.10.2016.
 */

public class CalculationNumber extends CalculationObject {

    private BigDecimal value;
    private boolean hasDecimalPoint = false;
    private boolean hasNumberAfterDecimalPoint = false;
    private final int HEIGHT = 40;
    private final int TEXT_SIZE = 40;

    public CalculationNumber(int num) {
        this(new BigDecimal(Double.toString((double) num)));
    }

    public CalculationNumber(double num) {
        this(new BigDecimal(Double.toString(num)));
    }

    public CalculationNumber(BigDecimal num) {
        super(Constants.CalculationSequence.NUMBER);
        if (num.doubleValue() % 1 != 0) {
            hasDecimalPoint = true;
            hasNumberAfterDecimalPoint = true;
        }
        this.value = num;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public int getWidth() {
//        color.setTextSize(TEXT_SIZE);
        return (int) color.measureText(value.toString()) + 2 * STANDARD_MARGIN;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight());
        relation = relation / 250;
        color.setTextSize((float) (TEXT_SIZE * relation));
        canvas.drawText(toString(), bounds.left + STANDARD_MARGIN, bounds.centerY(), color);
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
                return this.value.toString();//removeZerosFromEnd();
            } else {
                return Integer.toString(this.value.intValue()) + ".";
            }
        } else {
            return Integer.toString(this.value.intValue());
        }

    }

    public void removeZerosFromEnd() {
        String number = toString();
        int indexOfDot = number.indexOf('.');
        int lastIndex;
        int lastZeroIndex;
        while (indexOfDot != -1) {
            lastIndex = number.length() - 1;
            lastZeroIndex = number.lastIndexOf('0', lastIndex);
            if (lastZeroIndex == lastIndex && zeroNotBehindDot(indexOfDot, lastZeroIndex)) {
                number = number.substring(0, lastIndex);
            } else {
                break;
            }
        }
        this.value = new BigDecimal(number);
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

    public Object clone() {
        CalculationNumber n;
        n = (CalculationNumber) super.clone();
        n.value = value;
        return n;
    }
}
