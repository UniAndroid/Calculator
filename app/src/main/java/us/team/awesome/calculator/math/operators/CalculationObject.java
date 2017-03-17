package us.team.awesome.calculator.math.operators;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import us.team.awesome.calculator.util.DivideByZeroException;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by Stefan on 21.02.2017.
 */

public abstract class CalculationObject extends Drawable implements Cloneable{
    private Integer calculationSequence;
    private int alpha;
    protected Paint color;
    protected final int STANDARD_MARGIN = 15;

    public CalculationObject(int calculationSequence) {
        this.calculationSequence = calculationSequence;
        this.alpha = 255;

        color = new Paint();
        color.setStyle(Paint.Style.STROKE);
        color.setColor(Color.BLACK);
        color.setTextSize(50);
        color.setStrokeWidth(10);
    }

    public abstract BigDecimal getValue() throws MathException;

    public abstract int getWidth();

    public Object clone(){
        CalculationObject o = null;
        try {
            o = (CalculationObject) super.clone();
            o.calculationSequence = new Integer(this.calculationSequence);
        } catch (CloneNotSupportedException e) {} // Won't happen
        return o;
    }


    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getAlpha() {
        return this.alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        //override dummy, falls wir mal mit Filtern arbeiten wollen
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
