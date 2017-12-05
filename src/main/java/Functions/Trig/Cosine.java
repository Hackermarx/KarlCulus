package Functions.Trig;

import Functions.*;

public class Cosine extends TrigFunction {
    public Cosine(Expression child, boolean isArc) {
        super(child, isArc);
    }

    public Cosine(Expression child) {
        super(child, false);
    }

    public Cosine() {
        super(new Var(), false);
    }

    @Override
    public double stdEval(Input in) {
        return Math.cos(child.eval(in));
    }

    @Override
    public double arcEval(Input in) {
        return Math.acos(child.eval(in));
    }

    @Override
    public Complex stdEval(Complex in) {
        return Complex.cos(child.eval(in));
    }

    @Override
    public Complex arcEval(Complex in) {
        return Complex.arccos(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Negative(new Sine(child)), child.derivative(to));
    }

    @Override
    public String toString() {
        return super.toString() + "cos(" + child + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cosine cosine = (Cosine) o;

        return child != null ? child.equals(cosine.child) : cosine.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
