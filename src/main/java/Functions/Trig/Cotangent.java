package Functions.Trig;

import Functions.*;

public class Cotangent extends TrigFunction {
    public Cotangent(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Cotangent(Expression child) {
        super(child, false);
    }

    public Cotangent() {
        super(new Var(), false);
    }

    @Override
    public double stdEval(Input in) {
        return 1 / Math.tan(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return Math.atan(1 / child.eval(in));
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.cot(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return Complex.arccot(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Negative(new Power(new Cosecant(child), 2)), child.derivative(to));
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
        return super.toString() + "cot(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cotangent cotangent = (Cotangent) o;

        return child != null ? child.equals(cotangent.child) : cotangent.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
