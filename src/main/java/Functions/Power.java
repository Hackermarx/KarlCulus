package Functions;

public class Power extends Expression {
    private Expression base;
    private double exponent;

    public Power(Expression base, double exponent) {
        super();
        this.base = base;
        this.exponent = exponent;
    }

    public Power(double exponent) {
        super();
        this.base = new Var();
        this.exponent = exponent;
    }

    @Override
    public double eval(Input in) {
        return Math.pow(base.eval(in), exponent);
    }

    @Override
    public Complex eval(Complex in) {
        return Complex.pow(base.eval(in), exponent);
    }

    @Override
    public Expression derivative(String to) {
        if (exponent == -1) {
            return new Reciprocal(base).derivative(to);
        } else {
            return new Product(new Constant(exponent), new Power(base, exponent - 1), base.derivative(to));
        }
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return base;
        } else {
            throw new IllegalArgumentException("Invalid identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId == 0) {
            base = expr;
        } else {
            throw new IllegalArgumentException("Illegal identifier");
        }
    }

    @Override
    public String toString() {
        return base + (exponent == 1.0 ? "" : " ^ " + exponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Power power = (Power) o;

        return Double.compare(power.exponent, exponent) == 0 && (base != null ? base.equals(power.base) : power.base == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = base != null ? base.hashCode() : 0;
        temp = Double.doubleToLongBits(exponent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
