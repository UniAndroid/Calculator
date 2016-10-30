package us.team.awesome.calculator.math.operators;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.CalculationNumber;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 14.10.2016.
 */

public abstract class CalculationOperator extends Drawable {
    private String value;
    private Paint paint;

    public CalculationOperator(String value) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setStrokeWidth(4);

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public BigDecimal getNumberBeforeOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index - 1)).getValue();
    }

    public BigDecimal getNumberAfterOperator(int index, CalculationList list) {
        return ((CalculationNumber) list.get(index + 1)).getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CalculationOperator) {
            return Objects.equals(((CalculationOperator) obj).getValue(), value);
        }
        return super.equals(obj);
    }

    public abstract CalculationList calculate(int index, CalculationList list) throws MathException;

    @Override
    public void setAlpha(int alpha) {
        //override dummy, falls wir mal mit Transparenz arbeiten wollen
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        //override dummy, falls wir mal mit Filtern arbeiten wollen
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public String toString(){
        return value;
    }

    public Paint getPaint() {
        return this.paint;
    }
}
