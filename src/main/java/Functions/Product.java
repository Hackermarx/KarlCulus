package Functions;

import java.util.*;

public class Product extends Expression {
    private Expression[] operands;

    public Product(Expression... operands) {
        super();

        ArrayList<Expression> tempOp = new ArrayList<>(Arrays.asList(operands));
        double constant = 1;
        Expression temp;
        for (int i = tempOp.size() - 1; i >= 0; i--) {
            temp = tempOp.get(i);
            if (temp instanceof Constant) {
                constant *= temp.eval(new Input(0));
                tempOp.remove(i);
            }
        }
        if (constant != 1) {
            tempOp.add(0, new Constant(constant));
        }
        if (constant == 0) {
//            getParent().setExp(getParentExprId(), new Constant(0));
        }

        this.operands = tempOp.toArray(new Expression[tempOp.size()]);
    }

    @Override
    public double eval(Input in) {
        double ret = 1;
        for (Expression f : operands) {
            ret *= f.eval(in);
        }
        return ret;
    }

    @Override
    public Complex eval(Complex in) {
        Complex ret = new Complex(1, 0);
        for (Expression f : operands) {
            ret = Complex.mul(ret, f.eval(in));
        }
        return ret;
    }

    @Override
    public Expression derivative(String to) {
        int opLength = operands.length;
        Product[] ret = new Product[opLength];
        Expression[] termOfRet;
        for (int i = 0; i < opLength; i++) {
            termOfRet = new Expression[opLength];
            for (int j = 0; j < opLength; j++) {
                if (i == j) {
                    termOfRet[j] = operands[j].derivative(to);
                } else {
                    termOfRet[j] = operands[j];
                }
            }
            ret[i] = new Product(termOfRet);
        }
        return new Sum(ret);
    }

    @Override
    public Expression getExp(int exprId) {
        if (exprId >= 0 && exprId <= operands.length - 1) {
            return operands[exprId];
        } else {
            throw new IllegalArgumentException("Illegal identifier");
        }
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        if (exprId >= 0 && exprId <= operands.length - 1) {
            operands[exprId] = expr;
        } else {
            throw new IllegalArgumentException("Illegal identifier");
        }
    }

    @Override
    public String toString() {
        String ret = "(";
        if (operands.length == 0) {
            return "";
        }
        for (int i = 0; i < operands.length - 1; i++) {
            ret += (operands[i] + " * ");
        }
        ret += operands[operands.length - 1] + ")";
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        for (int i = 0; i < operands.length; i++) {
            if (!operands[i].equals(product.operands[i])) {
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
