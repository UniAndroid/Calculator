package us.team.awesome.calculator.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

import us.team.awesome.calculator.activities.SettingsActivity;
import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.math.Calculator;
import us.team.awesome.calculator.math.operators.basic.CalculationNumber;
import us.team.awesome.calculator.util.MathException;

/**
 * Created by JWO on 17.10.2016.
 */

public class EquationView extends View {


    private Paint backgroundPainter;
    private Paint foregroundPainter;

    private String ergebnisString = "";

    private CalculationList calculationList;
    private Calculator calculator;

    private boolean autoCalculate;
    private boolean customDrawer;

    public EquationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        this.calculationList = new CalculationList(true);
        this.calculator = new Calculator(calculationList);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        autoCalculate = sharedPref.getBoolean(SettingsActivity.AUTO_CALCULATE, false);
        customDrawer = sharedPref.getBoolean(SettingsActivity.CUSTOM_DRAWER, false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPainter);
        if (customDrawer) {
            calculator.setBounds(0, 0, getWidth(), getHeight());
            System.out.println("EquationView bounds for calculator" + calculator.getBounds().toString());
            calculator.draw(canvas);
        } else {
            canvas.drawText(this.calculationList.toString(), 0, 50, foregroundPainter);
        }
        if (!this.ergebnisString.isEmpty()) {
            canvas.drawText(this.ergebnisString, getWidth() - (this.ergebnisString.length() * 30), getHeight() - 50, foregroundPainter);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    private void init(Context context) {
        this.backgroundPainter = new Paint();
        this.backgroundPainter.setStyle(Paint.Style.FILL);
        this.backgroundPainter.setColor(Color.WHITE);

        this.foregroundPainter = new Paint();
        this.foregroundPainter.setStyle(Paint.Style.STROKE);
        this.foregroundPainter.setColor(Color.BLACK);
        this.foregroundPainter.setTextSize(50);
    }

    public void addNumber(int number) {
        this.calculationList.addNumber(number);
        if (autoCalculate) {
            calculate();
        } else {
            this.updateEquation();
        }
    }

    public void addNumber(String number) {
        int _num = Integer.parseInt(number);
        this.addNumber(_num);
    }

    public void addAddOperator() {
        this.calculationList.addAddOperator();
        this.updateEquation();
    }

    public void addSubtractOperator() {
        this.calculationList.addSubtractOperator();
        this.updateEquation();
    }

    public void addMultiplyOperator() {
        this.calculationList.addMultiplyOperator();
        this.updateEquation();
    }

    public void addDivideOperator() {
        this.calculationList.addDivideOperator();
        this.updateEquation();
    }

    public void addDecimalPoint() {
        this.calculationList.addDecimalPoint();
        this.updateEquation();
    }

    public void addLeftBracket() {
        this.calculationList.addLeftBracket();
        this.updateEquation();
    }

    public void addRightBracket() {
        this.calculationList.addRightBracket();
        this.updateEquation();
    }

    public void clear() {
        this.calculationList = new CalculationList(true);
        this.ergebnisString = "";
        this.updateEquation();
    }

    public void calculate() {
        try {
            calculator.setEquation(this.calculationList);
            CalculationNumber result = calculator.getCalculationResult();
            this.ergebnisString = result.toString();
        } catch (MathException e) {
            this.ergebnisString = "ERROR";
            e.printStackTrace();
        }
        this.invalidate();
    }

    public String getCalculationString() {
        return calculationList.toString();
    }

    public CalculationList getCalculationList() {
        return calculationList;
    }

    public void setCalculationList(CalculationList calculationList) {
        this.calculationList = calculationList;
        this.updateEquation();
    }

    /**
     * Ruft die onDraw() Methode erneut auf
     */
    private void updateEquation() {
        // dadurch wird die onDraw() Methode erneut aufgerufen!
        calculator.setEquation(this.calculationList);
        this.invalidate();
    }
}
