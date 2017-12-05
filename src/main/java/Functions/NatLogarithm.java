package Functions;

public class NatLogarithm extends Logarithm {
    public NatLogarithm (Expression input) {
        super(new Constant(Math.E), input);
    }

    @Override
    public double eval(Input in) {
        return Math.log(super.getInput().eval(in));
    }

    @Override
    public Expression derivative(String to) {
        return new Product(new Reciprocal(super.getInput()), super.getInput().derivative(to));
    }

    @Override
    public String toString() {
        return "ln(" + super.getInput() + ")";
    }
}
