package Functions;

public class Polynomial extends Expression {
    private Sum terms;

    public Polynomial(Sum terms) {
        super();
        this.terms = terms;
    }

    public Polynomial(double... terms) {
        super();
        Product[] temp = new Product[terms.length];
        int maxExp = terms.length;
        for (int i = 0; i < terms.length - 1; i++) {
            temp[i] = new Product(new Constant(terms[i]), new Power(maxExp - i));
        }
        this.terms = new Sum(temp);
    }

    @Override
    public double eval(Input in) {
        return terms.eval(in);
    }

    @Override
    public Complex eval(Complex in) {
        return terms.eval(in);
    }

    @Override
    public Expression derivative(String to) {
        return terms.derivative(to);
    }

    @Override
    public Expression getExp(int exprId) {
        return terms.getExp(exprId);
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        terms.setExp(exprId, expr);
    }

    public String toString() {
        return terms.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polynomial that = (Polynomial) o;

        return terms != null ? terms.equals(that.terms) : that.terms == null;
    }

    @Override
    public int hashCode() {
        return terms != null ? terms.hashCode() : 0;
    }
}
