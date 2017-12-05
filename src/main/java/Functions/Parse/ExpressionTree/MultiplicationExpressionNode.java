package Functions.Parse.ExpressionTree;

import Functions.*;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationExpressionNode extends SequenceExpressionNode {

    public MultiplicationExpressionNode() {
        super();
    }

    public MultiplicationExpressionNode(ExpressionNode a, boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return ExpressionNode.MULTIPLICATION_NODE;
    }


    public Expression getValue() {
        List<Expression> num = new ArrayList<>();
        List<Expression> den = new ArrayList<>();
        for (Term term : terms) {
            if (term.positive) {
                num.add(term.expression.getValue());
            } else {
                den.add(term.expression.getValue());
            }
        }
        if (den.size() == 0) {
            return new Product(num.toArray(new Expression[num.size()]));
        } else {
            return new Fraction(
                    new Product(num.toArray(new Expression[num.size()])),
                    new Product(den.toArray(new Expression[den.size()]))
            );
        }
    }
}