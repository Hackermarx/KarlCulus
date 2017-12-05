package Functions.Parse.ExpressionTree;

import Functions.Expression;
import Functions.Negative;
import Functions.Sum;

public class AdditionExpressionNode extends SequenceExpressionNode {
    public AdditionExpressionNode() {
        super();
    }

    public AdditionExpressionNode(ExpressionNode a, boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return ExpressionNode.ADDITION_NODE;
    }

    public Sum getValue() {
        Expression[] ret = new Expression[terms.size()];
        for (int i = 0; i < terms.size(); i++) {
            ret[i] = terms.get(i).expression.getValue();
            if (!terms.get(i).positive) {
                ret[i] = new Negative(ret[i]);
            }
        }
        return new Sum(ret);
    }
}
