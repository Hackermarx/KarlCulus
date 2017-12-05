package Functions;

import static Functions.Complex.*;

public class Exponent extends Expression {
    private Expression base;
    private Expression exponent;

    public Exponent(Expression base, Expression exponent) {
        super();
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public double eval(Input in) {
        return Math.pow(base.eval(in), exponent.eval(in));
    }

    @Override
    public Complex eval(Complex in) {
        return exp(mul(log(base.eval(in)), exponent.eval(in)));
    }

    @Override
    public Expression derivative(String to) {
        Expression temp = new Product(new NatLogarithm(base), exponent);
        return new Product(
                new NatExp(temp),
                new Sum(
                        new Product(exponent, new Reciprocal(base), base.derivative(to)),
                        new Product(exponent.derivative(to), base)
                )
        );
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return base;
        } else if (exprId == 1) {
            return exponent;
        } else {
            throw new IllegalArgumentException("Invalid expression identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId == 0) {
            base = expr;
        } else if (exprId == 1) {
            exponent = expr;
        } else {
            throw new IllegalArgumentException("Invalid identifier");
        }
    }

    @Override
    public String toString() {
        return base + " ^ " + exponent;
    }

    public Expression getBase() {
        return base;
    }

    public Expression getExponent() {
        return exponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exponent exponent1 = (Exponent) o;

        if (base != null ? !base.equals(exponent1.base) : exponent1.base != null) return false;
        return exponent != null ? exponent.equals(exponent1.exponent) : exponent1.exponent == null;
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (exponent != null ? exponent.hashCode() : 0);
        return result;
    }
}
