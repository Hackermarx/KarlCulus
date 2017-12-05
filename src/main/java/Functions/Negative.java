package Functions;

import static Functions.Complex.*;

public class Negative extends Expression {
    private Expression child;

    public Negative(Expression child) {
        super();
        this.child = child;
    }

    public Negative() {
        super();
        this.child = new Var();
    }

    @Override
    public double eval(Input in) {
        return -child.eval(in);
    }

    @Override
    public Complex eval(Complex in) {
        return neg(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Negative(child.derivative(to));
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return child;
        } else {
            throw new IllegalArgumentException("Invalid expression identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId == 0) {
            child = expr;
        } else {
            throw new IllegalArgumentException("Invalid identifier");
        }
    }

    @Override
    public String toString() {
        return "-" + child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Negative negative = (Negative) o;

        return child != null ? child.equals(negative.child) : negative.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
