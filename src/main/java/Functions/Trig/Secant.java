package Functions.Trig;

import Functions.*;

public class Secant extends TrigFunction {
    public Secant(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Secant(Expression child) {
        super(child, false);
    }

    public Secant() {
        super(new Var(), false);
    }

    @Override
    public double stdEval(Input in) {
        return 1 / Math.cos(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return Math.acos(1 / child.eval(in));
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.sec(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return Complex.arcsec(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Secant(child), new Tangent(child), child.derivative(to));
    }

    @Override
    public Expression getExp(int exprId) {
        return child;
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        this.child = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "sec(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Secant secant = (Secant) o;

        return child != null ? child.equals(secant.child) : secant.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
