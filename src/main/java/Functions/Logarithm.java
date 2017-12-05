package Functions;

import static Functions.Complex.*;

public class Logarithm extends Expression {
    private Expression base;
    private Expression input;

    public Logarithm(Expression base, Expression input) {
        super();
        this.base = base;
        this.input = input;
    }

    public Logarithm() {
        super();
        this.base = new Constant(Math.E);
        this.input = new Var();
    }

    public Logarithm(double base, Expression input) {
        super();
        this.base = new Constant(base);
        this.input = input;
    }

    @Override
    public double eval(Input in) {
        return Math.log(input.eval(in)) / Math.log(base.eval(in));
    }

    @Override
    public Complex eval(Complex in) {
        return div(log(input.eval(in)), log(base.eval(in)));
    }

    @Override
    public Expression derivative(String to) {
        if (base instanceof Constant) {
            Constant constBase = (Constant) base;
            return new Fraction(
                    input.derivative(to),
                    new Product(input, new NatLogarithm(new Constant(constBase.eval(new Input(0)))))
            );
        } else {
            return new Fraction(
                    new Sum(
                        new Product(new Logarithm(10, input), base.derivative(to), input),
                        new Negative(new Product(new Logarithm(10, base), input.derivative(to), base))
                    ),
                    new Product(new Constant(Math.log(10) / Math.log(Math.E)),
                            base, input, new Power(new Logarithm(10, base), 2))
            );
        }
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return base;
        } else if (exprId == 1) {
            return input;
        } else {
            throw new IllegalArgumentException("Invalid expression identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId == 0) {
            base = expr;
        } else if (exprId == 1) {
            input = expr;
        } else {
            throw new IllegalArgumentException("Invalid expression identifier");
        }
    }

    @Override
    public String toString() {
        return "log_" + base + "(" + input + ")";
    }

    public Expression getBase() {
        return base;
    }

    public Expression getInput() {
        return input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logarithm logarithm = (Logarithm) o;

        if (base != null ? !base.equals(logarithm.base) : logarithm.base != null) return false;
        if (input != null ? !input.equals(logarithm.input) : logarithm.input != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (input != null ? input.hashCode() : 0);
        return result;
    }
}
