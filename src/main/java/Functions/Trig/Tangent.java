package Functions.Trig;

import Functions.*;

public class Tangent extends TrigFunction {
    public Tangent(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Tangent(Expression child) {
        super(child, false);
    }

    public Tangent() {
        super(new Var(), false);
    }

    @Override
    public double stdEval(Input in) {
        return Math.tan(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return 0;
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.tan(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return null;
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Power(new Secant(child), 2), child.derivative(to));
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
        return super.toString() + "tan(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tangent tangent = (Tangent) o;

        return child != null ? child.equals(tangent.child) : tangent.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
