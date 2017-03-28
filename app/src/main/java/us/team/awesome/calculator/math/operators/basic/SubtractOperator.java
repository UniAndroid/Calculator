package us.team.awesome.calculator.math.operators.basic;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.CalculationOperator;
import us.team.awesome.calculator.util.Constants;
import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.EquationMalformedException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 14.10.2016.
 */

public class SubtractOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject minuend;
    private CalculationObject subtrahend;
    private final int WIDTH = 40;
    private final int HEIGHT = WIDTH;

    public SubtractOperator() {
        super(Constants.CalculationSequence.SUBTRACT);
    }

    @Override
    public BigDecimal getValue() throws MathException {
        if(minuend == null || subtrahend == null) {
            throw new EquationMalformedException();
        }
        BigDecimal _minuend = minuend.getValue();
        BigDecimal _subtrahend = subtrahend.getValue();
        return _minuend.subtract(_subtrahend);
    }

    @Override
    public int getWidth() {
        int minuendWidth = minuend != null ? minuend.getWidth() : 0;
        int subtrahendWidth = subtrahend != null ? subtrahend.getWidth() : 0;
        return minuendWidth + WIDTH + subtrahendWidth + 2*STANDARD_MARGIN;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return minuend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return subtrahend;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject minuend) {
        this.minuend = minuend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SubtractOperator) {
            SubtractOperator compareOperator = (SubtractOperator) obj;
            boolean sameMinuend = Objects.equals(compareOperator.minuend, this.minuend);
            boolean sameSubtrahend = Objects.equals(compareOperator.subtrahend, this.subtrahend);
            return sameMinuend && sameSubtrahend;
        }
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(minuend != null) {
            sb.append(minuend.toString());
        }
        sb.append("-");
        if(subtrahend != null) {
            sb.append(subtrahend.toString());
        }
        return sb.toString();
    }

    public Object clone() {
        SubtractOperator s;
        s = (SubtractOperator) super.clone();
        if (minuend != null) {
            s.minuend = (CalculationObject) minuend.clone();
        }
        if (subtrahend != null) {
            s.subtrahend = (CalculationObject) subtrahend.clone();
        }
        return s;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        int minuendWidth = minuend != null ?  minuend.getWidth() : 0;
        Rect minuendBounds = new Rect(bounds.left, bounds.top, minuendWidth, bounds.bottom);
        if(minuend != null) {
            minuend.setBounds(minuendBounds);
            minuend.draw(canvas);
        }

        drawSubtractOperator(canvas, bounds, minuendBounds);

        if(subtrahend != null) {
            int subtrahendWidth = subtrahend.getWidth();
            Rect subtrahendBounds = new Rect(getWidth() - subtrahendWidth, bounds.top, bounds.right, bounds.bottom);
            subtrahend.setBounds(subtrahendBounds);
            subtrahend.draw(canvas);
        }
    }

    //############private############

    private void drawSubtractOperator(Canvas canvas, Rect bounds, Rect minuendBounds) {
        int centerX = minuendBounds.right + STANDARD_MARGIN + WIDTH/2;
        int centerY = (bounds.bottom - bounds.top) / 2;

        //horizontal line
        int horizontalStartX = centerX - WIDTH/2;
        canvas.drawLine(horizontalStartX, centerY, horizontalStartX + WIDTH, centerY, color);
    }
}
