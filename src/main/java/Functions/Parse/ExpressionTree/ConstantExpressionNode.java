package Functions.Parse.ExpressionTree;

import Functions.Constant;

public class ConstantExpressionNode implements ExpressionNode {
    private Constant value;

    public ConstantExpressionNode(double value) {
        this.value = new Constant(value);
    }

    public ConstantExpressionNode(String value) {
        this.value = new Constant(Double.valueOf(value));
    }

    @Override
    public int getType() {
        return ExpressionNode.CONSTANT_NODE;
    }

    @Override
    public Constant getValue() {
        return value;
    }
}
