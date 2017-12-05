package Functions;

import java.util.ArrayList;
import java.util.Arrays;

public class Sum extends Expression {
    private Expression[] operands;

    public Sum(Expression... operands) {
        super();

        ArrayList<Expression> tempOp = new ArrayList<>(Arrays.asList(operands));
        double constant = 0;
        Expression temp;
        for (int i = tempOp.size() - 1; i >= 0; i--) {
            temp = tempOp.get(i);
            if (temp instanceof Constant) {
                constant += temp.eval(new Input(0));
                tempOp.remove(i);
            }
        }
        if (constant != 0) {
            tempOp.add(new Constant(constant));
        }
        this.operands = tempOp.toArray(new Expression[tempOp.size()]);
    }

    @Override
    public double eval(Input in) {
        double ret = 0;
        for (Expression f : operands) {
            ret += f.eval(in);
        }
        return ret;
    }

    @Override
    public Complex eval(Complex in) {
        Complex ret = new Complex(0, 0);
        for (Expression f : operands) {
            ret = Complex.add(ret, f.eval(in));
        }
        return ret;
    }

    @Override
    public Expression derivative(String to) {
        Expression[] ret = new Expression[operands.length];
        for (int i = 0; i < operands.length; i++) {
            ret[i] = operands[i].derivative(to);
        }
        return new Sum(ret);
    }

    @Override
    public Expression getExp(int exprId) {
        return operands[exprId];
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        operands[exprId] = expr;
    }

    @Override
    public String toString() {
        String ret = "(";
        if (operands.length == 0) {
            return "";
        }
        for (int i = 0; i < operands.length - 1; i++) {
            ret += (operands[i] + " + ");
        }
        ret += operands[operands.length - 1] + ")";
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum sum = (Sum) o;

        for (int i = 0; i < operands.length; i++) {
            if (!operands[i].equals(sum.operands[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(operands);
    }
}
