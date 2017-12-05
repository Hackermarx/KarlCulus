package Functions.Trig;

import Functions.*;

public class Cosecant extends TrigFunction {
    public Cosecant(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Cosecant(Expression child) {
        super(child, false);
    }

    public Cosecant() {
        super(new Var(), false);
    }

    @Override
    public double stdEval(Input in) {
        return 1 / Math.sin(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return Math.acos(1 / child.eval(in));
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.csc(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return Complex.arccsc(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Negative(new Product(new Cosecant(child), new Cotangent(child), child.derivative(to)));
    }

    @Override
    public Expression getExp(int exprId) {
        return child;
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        this.child = expr;
    }

    public String toString() {
        return super.toString() + "csc(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cosecant cosecant = (Cosecant) o;

        return child != null ? child.equals(cosecant.child) : cosecant.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
