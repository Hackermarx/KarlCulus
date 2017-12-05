package Functions.Parse.ExpressionTree;

import java.util.LinkedList;

public abstract class SequenceExpressionNode implements ExpressionNode {
    protected LinkedList<Term> terms;

    public SequenceExpressionNode() {
        this.terms = new LinkedList<>();
    }

    public SequenceExpressionNode(ExpressionNode a, boolean positive) {
        this.terms = new LinkedList<>();
        this.terms.add(new Term(positive, a));
    }

    public void add(ExpressionNode a, boolean positive) {
        this.terms.add(new Term(positive, a));
    }

    public class Term {
        public boolean positive;
        public ExpressionNode expression;


        public Term(boolean positive, ExpressionNode expression) {
            this.positive = positive;
            this.expression = expression;
        }
    }
}
