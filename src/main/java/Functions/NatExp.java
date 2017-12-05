package Functions;

public class NatExp extends Exponent {
    public NatExp (Expression exponent) {
        super(new Constant(Math.E), exponent);
    }

    public NatExp() {
        super(new Constant(Math.E), new Var());
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new NatExp(super.getBase()), super.getBase().derivative(to));
    }

    @Override
    public String toString() {
        return "e ^ " + super.getExponent();
    }
}
