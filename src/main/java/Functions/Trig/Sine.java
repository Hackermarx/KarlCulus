package Functions.Trig;

import Functions.*;

public class Sine extends TrigFunction {
    public Sine(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Sine(Expression child) {
        super(child, false);
    }

    public Sine() {
        super(new Var(), false);
    }

    /**
     * Evaluates the sine at the given
     * @param in The inpu at which to evaluate
     * @return The value of the sine
     */
    @Override
    public double stdEval(Input in) {
        return Math.sin(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return Math.asin(child.eval(in));
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.sin(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return Complex.arcsin(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Cosine(child), child.derivative(to));
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
        return super.toString() + "sin(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sine sine = (Sine) o;

        return child != null ? child.equals(sine.child) : sine.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
