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

public class DivideOperator extends CalculationObject implements CalculationOperator {

    private CalculationObject dividend;
    private CalculationObject divisor;
    private final int WIDTH = 60;
    private final int HEIGHT = 80;

    public DivideOperator() {
        super(Constants.CalculationSequence.DIVIDE);
    }

    @Override
    public BigDecimal getValue() throws MathException {
        if (dividend == null || divisor == null) {
            throw new EquationMalformedException();
        }
        BigDecimal _dividend = dividend.getValue();
        BigDecimal _divisor = divisor.getValue();
        try {
            return _dividend.divide(_divisor);
        } catch (ArithmeticException e) {
            throw new DivideByZeroException("Devide by zero not allowed");
        }
    }

    @Override
    public int getWidth() {
        int dividendWidth = dividend != null ? dividend.getWidth() : 0;
        int divisorWidth = divisor != null ? divisor.getWidth() : 0;
        int divideOperatorWidth = dividendWidth >= divisorWidth ? dividendWidth : divisorWidth;
        if(WIDTH > divideOperatorWidth) {
            return WIDTH;
        }else{
            return divideOperatorWidth;
        }
    }

    @Override
    public int getHeight() {
        int dividendHeight = dividend != null ? dividend.getHeight() : 0;
        int divisorHeight = divisor != null ? divisor.getHeight() : 0;
        int divideOperatorHeight = dividendHeight + divisorHeight + STANDARD_MARGIN * 2;
        if(HEIGHT > divideOperatorHeight) {
            return HEIGHT;
        }else{
            return divideOperatorHeight;
        }
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return dividend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return divisor;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject dividend) {
        this.dividend = dividend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DivideOperator) {
            DivideOperator compareOperator = (DivideOperator) obj;
            boolean sameDividend = Objects.equals(compareOperator.dividend, this.dividend);
            boolean sameDivisor = Objects.equals(compareOperator.divisor, this.divisor);
            return sameDividend && sameDivisor;
        }
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dividend != null) {
            sb.append(dividend.toString());
        }
        sb.append(":");
        if (divisor != null) {
            sb.append(divisor.toString());
        }
        return sb.toString();
    }

    public Object clone() {
        DivideOperator d;
        d = (DivideOperator) super.clone();
        if (dividend != null) {
            d.dividend = (CalculationObject) dividend.clone();
        }
        if (divisor != null) {
            d.divisor = (CalculationObject) divisor.clone();
        }
        return d;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        int dividendBottom = dividend != null ? bounds.top + dividend.getHeight() : bounds.bottom;
        Rect dividendBounds = new Rect(bounds.left, bounds.top + STANDARD_MARGIN, bounds.right, dividendBottom);
        if(dividend != null) {
            dividend.setBounds(dividendBounds);
            dividend.draw(canvas);
        }

        drawDivideOperator(canvas, bounds, dividendBounds);

        if(divisor != null) {
            Rect divisorBounds = new Rect(bounds.left, dividendBounds.bottom, bounds.right, bounds.bottom);
            divisor.setBounds(divisorBounds);
            divisor.draw(canvas);
        }
    }

    //############private############

    private void drawDivideOperator(Canvas canvas, Rect bounds, Rect dividendBounds) {
        int startY = dividendBounds.bottom + STANDARD_MARGIN;
        int startX = bounds.left + STANDARD_MARGIN;

        canvas.drawLine(startX, startY, startX + getWidth(), startY, color);
    }
}
