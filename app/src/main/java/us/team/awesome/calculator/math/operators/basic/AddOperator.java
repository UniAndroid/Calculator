package us.team.awesome.calculator.math.operators.basic;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

public class AddOperator extends CalculationObject implements CalculationOperator{

    private CalculationObject augend; // left
    private CalculationObject addend; // right
    private final int WIDTH = 40;
    private final int HEIGHT = WIDTH;

    public AddOperator() {
        super(Constants.CalculationSequence.ADD);
    }

    @Override
    public BigDecimal getValue() throws MathException {
        if (augend == null || addend == null) {
            throw new EquationMalformedException();
        }
        BigDecimal _augend = augend.getValue();
        BigDecimal _addend = addend.getValue();
        return _augend.add(_addend);
    }

    @Override
    public CalculationObject getLeftCalculationObject() {
        return augend;
    }

    @Override
    public CalculationObject getRightCalculationObject() {
        return addend;
    }

    @Override
    public void setLeftCalculationObject(CalculationObject augend) {
        this.augend = augend;
    }

    @Override
    public void setRightCalculationObject(CalculationObject addend) {
        this.addend = addend;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AddOperator) {
            AddOperator compareOperator = (AddOperator) obj;
            boolean sameAugend = Objects.equals(compareOperator.augend, this.augend);
            boolean sameAddend = Objects.equals(compareOperator.addend, this.addend);
            return sameAddend && sameAugend;
        }
        return super.equals(obj);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        int augendWidth = augend != null ?  augend.getWidth() : 0;
        Rect augendBounds = new Rect(bounds.left, bounds.top, augendWidth, bounds.bottom);
        if(augend != null) {
            augend.setBounds(augendBounds);
            augend.draw(canvas);
        }

        drawAddOperator(canvas, bounds, augendBounds);

        if(addend != null) {
            int addendWidth = addend.getWidth();
            Rect addendBounds = new Rect(getWidth() - addendWidth, bounds.top, bounds.right, bounds.bottom);
            addend.setBounds(addendBounds);
            addend.draw(canvas);
        }
    }

    @Override
    public int getWidth() {
        int augendWidth = augend != null ? augend.getWidth() : 0;
        int addendWidth = addend != null ? addend.getWidth() : 0;
        return augendWidth + WIDTH + addendWidth + 2*STANDARD_MARGIN;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(augend != null) {
            sb.append(augend.toString());
        }
        sb.append("+");
        if(addend != null) {
            sb.append(addend.toString());
        }
        return sb.toString();
    }

    public Object clone() {
        AddOperator o;
        o = (AddOperator) super.clone();
        if (augend != null) {
            o.augend = (CalculationObject) augend.clone();
        }
        if (addend != null) {
            o.addend = (CalculationObject) addend.clone();
        }
        return o;
    }

    //############private############

    private void drawAddOperator(@NonNull Canvas canvas, Rect bounds, Rect augendBounds) {
        int centerX = augendBounds.right + STANDARD_MARGIN + WIDTH/2;
        int centerY = (bounds.bottom - bounds.top) / 2;

        //horizontal line
        int horizontalStartX = centerX - WIDTH/2;
        canvas.drawLine(horizontalStartX, centerY, horizontalStartX + WIDTH, centerY, color);

        //vertical line
        int verticalStartY = centerY - WIDTH/2;
        canvas.drawLine(centerX, verticalStartY, centerX, verticalStartY + WIDTH, color);
    }
}
