package Functions.Parse.ExpressionTree;

import Functions.*;

public class ExponentiationExpressionNode implements ExpressionNode {

    private ExpressionNode base;
    private ExpressionNode exponent;

    public ExponentiationExpressionNode(ExpressionNode base, ExpressionNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public int getType() {
        return ExpressionNode.EXPONENTIATION_NODE;
    }

    public Expression getValue() {
        if (exponent.getValue() instanceof Constant) {
            return new Power(base.getValue(), exponent.getValue().eval(new Input(0)));
        } else {
            return new Exponent(base.getValue(), exponent.getValue());
        }
    }
}