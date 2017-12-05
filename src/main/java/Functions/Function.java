package Functions;

import Functions.Parse.Parser;

import java.util.*;

import static Functions.Complex.*;

/**
 * Container class for a function
 */
public class Function {
    private Expression desc;
    private Set<String> inputs;

    public Function(Expression desc) {
        this.desc = desc;
        initInputs("x");
    }

    public Function(Expression desc, String... in) {
        this.desc = desc;
        initInputs(in);
    }

    public Function(String desc) {
        this.desc = Parser.parse(desc).getDesc();
        initInputs("x");
    }

    public Function(String desc, String... in) {
        this.desc = Parser.parse(desc).getDesc();
        initInputs(in);
    }

    private void initInputs(String... in) {
        inputs = new HashSet<>();
        inputs.addAll(Arrays.asList(in));
    }

    public Expression getDesc() {
        return desc;
    }

    public Set<String> getInputs() {
        return inputs;
    }

    public Function derivative() {
        if (inputs.size() == 1) {
            return new Function(desc.derivative((String) inputs.toArray()[0]));
        } else {
            throw new IllegalArgumentException("Please specify which parameter to differentiate");
        }
    }

    public Function derivative(String to) {
        if (inputs.contains(to)) {
            return new Function(desc.derivative(to));
        } else {
            throw new IllegalArgumentException("Specified variable not present in inputs");
        }
    }

    public Function[] gradient() {
        Function[] ret = new Function[inputs.size()];
        int i = 0;
        for (String in : inputs) {
            ret[i] = derivative(in);
            i++;
        }
        return ret;
    }

    public Function expand(int n) {
        double[] ret = new double[n];
        Function f = this;
        int factorial = 1;
        for (int i = 0; i < n; i++) {
            ret[i] = f.eval(0) / factorial;
            factorial = i != 0 ? factorial * i : 1;
            f = f.derivative();
        }
        return new Function(new Polynomial(ret));
    }

    public double eval(double in) {
        if (inputs.size() == 1) {
            Input input = new Input();
            input.addVal((String) inputs.toArray()[0], in);
            return eval(input);
        } else {
            throw new IllegalArgumentException("Not enough input");
        }
    }

    public double eval(Input in) {
        if (inputs.containsAll(in.getIds())) {
            return desc.eval(in);
        } else {
            throw new IllegalArgumentException("Invalid inputs");
        }
    }

    public Complex eval(Complex in) {
        return desc.eval(in);
    }

    public double root(double guess, int nItr) {
        Expression der = desc.derivative("x");
        for (int i = 0; i < nItr; i++) {
            guess -= desc.eval(new Input(guess)) / der.eval(new Input(guess));
        }
        return guess;
    }

    public Complex root(Complex guess, int nItr) {
        Expression der = desc.derivative("x");
        for (int i = 0; i < nItr; i++) {
            guess = add(guess, neg(div(desc.eval(guess), der.eval(guess))));
        }
        return guess;
    }

    public double root(double guess) {
        return root(guess, 100);
    }

    public Complex root(Complex guess) {
        return root(guess, 100);
    }

    public double extreme(double guess) {
        return derivative().root(guess);
    }

    public Function nthDerivative(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greater than or equal to 1");
        } else if (n == 1) {
            return derivative();
        } else {
            return derivative().nthDerivative(n - 1);
        }
    }

    @Override
    public String toString() {
        return "f" + inputs + " = " + desc.toString();
    }
}
