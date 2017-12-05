package Functions;

public class ComStant extends Expression {
    private Complex value;

    public ComStant(Complex value) {
        super();
        this.value = value;
    }

    @Override
    public double eval(Input in) {
        return value.getReal();
    }

    @Override
    public Complex eval(Complex in) {
        return value;
    }

    @Override
    public Expression derivative(String to) {
        return new ComStant(new Complex(0, 0));
    }

    @Override
    public Expression getExp(int exprId) {
        // TODO: Make sure this is correct
        return null;
    }

    @Override
    public void setExp(int exprId, Expression expr) {}

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComStant comStant = (ComStant) o;

        return value != null ? value.equals(comStant.value) : comStant.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
