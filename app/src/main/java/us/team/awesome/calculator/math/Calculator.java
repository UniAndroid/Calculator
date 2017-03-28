package us.team.awesome.calculator.math;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.math.BigDecimal;

import us.team.awesome.calculator.math.operators.CalculationObject;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 16.10.2016.
 */

/**
 * The Calculator calculates a equation given by an CalculationList.
 * The original CalculationList is not altered.
 */
public class Calculator extends Drawable {

    private CalculationList equation;
    private CalculationListParser parser;
    private CalculationObject term;

    public Calculator(CalculationList equation) {
        this.equation = equation;
        this.parser = new CalculationListParser(equation);
        refreshTerm();
    }

    public CalculationNumber getCalculationResult() throws MathException {
        refreshTerm();

        BigDecimal result = term.getValue();
        CalculationNumber num = new CalculationNumber(result);
        num.removeZerosFromEnd();
        return num;
    }

    public void setEquation(CalculationList calculationList) {
        this.equation = calculationList;
        refreshTerm();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        refreshTerm();
        Log.d("MESSAGE", "Drawing term: " + term);
        if (term != null) {
            term.setBounds(getBounds());
            canvas.setDensity(100);
            term.draw(canvas);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        //for future use, transparency
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public String toString() {
        return term.toString();
    }

    private void refreshTerm() {
        parser.setCalculationList(this.equation);
        term = parser.parseCalculationList();
    }
}
