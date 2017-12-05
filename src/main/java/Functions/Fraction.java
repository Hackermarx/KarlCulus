package Functions;

public class Fraction extends Expression {
    private Expression numerator;
    private Expression denomenator;

    public Fraction(Expression numerator, Expression denomenator) {
        super();
        this.numerator = numerator;
        this.denomenator = denomenator;
    }

    @Override
    public double eval(Input in) {
        return numerator.eval(in) / denomenator.eval(in);
    }

    @Override
    public Complex eval(Complex in) {
        return Complex.div(numerator.eval(in), denomenator.eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Fraction(
                new Sum(new Product(numerator.derivative(to), denomenator),
                        new Negative(new Product(numerator, denomenator.derivative(to)))),
                new Power(denomenator, 2)
        );
    }

    @Override
    public String toString() {
        return "(" + numerator + ") / (" + denomenator + ")";
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId == 0) {
            return numerator;
        } else if (exprId == 1) {
            return denomenator;
        } else {
            throw new IllegalArgumentException("Invalid identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId == 0) {
            numerator = expr;
        } else if (exprId == 1) {
            denomenator = expr;
        } else {
            throw new IllegalArgumentException("Invalid expression identifier");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        return (numerator != null ? numerator.equals(fraction.numerator) : fraction.numerator == null) &&
                (denomenator != null ? denomenator.equals(fraction.denomenator) : fraction.denomenator == null);
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denomenator != null ? denomenator.hashCode() : 0);
        return result;
    }
}
