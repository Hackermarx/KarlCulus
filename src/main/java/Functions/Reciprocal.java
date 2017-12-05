package Functions;

public class Reciprocal extends Expression {
    private Expression child;

    public Reciprocal(Expression child) {
        super();
        this.child = child;
    }

    public Reciprocal() {
        super();
        this.child = new Var();
    }

    @Override
    public double eval(Input in) {
        return 1 / child.eval(in);
    }

    @Override
    public Complex eval(Complex in) {
        return Complex.inv(child.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Fraction(
                new Negative(child.derivative(to)),
                new Power(child, 2)
        );
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return child;
        } else {
            throw new IllegalArgumentException("Invalid identifier");
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
        return "1 / " + child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reciprocal that = (Reciprocal) o;

        return child != null ? child.equals(that.child) : that.child == null;
    }

    @Override
    public int hashCode() {
        return child != null ? child.hashCode() : 0;
    }
}
