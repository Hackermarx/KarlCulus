package Functions.Parse.ExpressionTree;

import Functions.Var;

public class VariableExpressionNode implements ExpressionNode {
    private Var name;

    public VariableExpressionNode(String name) {
        this.name = new Var(name);
    }

    public int getType() {
        return ExpressionNode.VARIABLE_NODE;
    }

    public Var getValue() {
        return name;
    }
}
