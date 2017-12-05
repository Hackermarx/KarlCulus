package Functions;

public class Constant extends Expression {
    private double value;

    public Constant(double value) {
        super();
        this.value = value;
    }

    @Override
    public double eval(Input in) {
        return value;
    }

    @Override
    public Complex eval(Complex in) {
        return new Complex(value, 0);
    }

    @Override
    public Expression derivative(String to) {
        return new Constant(0);
    }

    @Override
    public Expression getExp(int exprId) {
        // TODO: Make sure this is correct
        return null;
    }

    @Override
    public void setExp(int exprId, Expression expr) {}

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constant constant = (Constant) o;

        return Double.compare(constant.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
