package us.team.awesome.calculator.math;

/**
 * Created by Stefan on 14.10.2016.
 */

class CalculationNumber {

    private double value;

    CalculationNumber(double num) {
        this.value = num;
    }

    double getValue() {
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
     * @param toDecimal should the Integer be attached to the decimals
     */
    void attacheNumber(int attachNum, boolean toDecimal) {
        // TODO wenn ein komma eingefügt wird und danach eine null, verschwindet das komma wieder
        // es sind also Zahlen wie 1.06 nicht möglich. kann durch zwischenspeicher gelöst werden

        StringBuilder sb = new StringBuilder(2);
        if (hasNoDecimalDigits()) {
            sb.append((int) value);
        } else {
            sb.append(value);
        }
        if (toDecimal) {
            sb.append(".");
        }
        sb.append(attachNum);
        this.value = Double.parseDouble(sb.toString());
    }

    boolean hasNoDecimalDigits() {
        return value % 1 == 0;
    }

    public String toString() {
        if(hasNoDecimalDigits()){
            return Integer.toString((int) value);
        }
        return Double.toString(value);
    }
}
