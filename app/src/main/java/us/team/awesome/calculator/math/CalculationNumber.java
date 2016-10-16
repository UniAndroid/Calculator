package us.team.awesome.calculator.math;

/**
 * Created by Stefan on 14.10.2016.
 */

class CalculationNumber {

    private String value;

    CalculationNumber(int num) {
        this.value = Integer.toString(num);
    }

    CalculationNumber(double num) {
        this.value = Double.toString(num);
    }

    double getValue() {
        return Double.parseDouble(value);
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
    void attacheNumber(int attachNum) {
        this.value += Integer.toString(attachNum);
    }

    void attachDecimalPoint() {
        if(!value.contains(".")){
            this.value += ".";
        }
    }

    boolean hasDecimalPoint() {
        return getValue() % 1 != 0;
    }

    public String toString() {
        return value;
    }
}
