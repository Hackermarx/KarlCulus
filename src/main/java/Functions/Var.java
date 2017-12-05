package Functions;

public class Var extends Expression {
    private String name;

    public Var() {
        super();
        this.name = "x";
    }

    public Var(String name) {
        super();
        this.name = name;
    }

    public double eval(Input in) {
        return in.getValueAt(name);
    }

    @Override
    public Complex eval(Complex in) {
        return in;
    }

    @Override
    public Expression derivative(String to) {
        if (to.equals(name)) {
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }

    @Override
    public Expression getExp(int exprId) {
        return null;
    }

    @Override
    public void setExp(int exprId, Expression expr) {}

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
