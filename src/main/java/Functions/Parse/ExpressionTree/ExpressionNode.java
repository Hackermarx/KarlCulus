package Functions.Parse.ExpressionTree;

import Functions.Expression;

public interface ExpressionNode {
    int VARIABLE_NODE = 1;
    int CONSTANT_NODE = 2;
    int ADDITION_NODE = 3;
    int MULTIPLICATION_NODE = 4;
    int EXPONENTIATION_NODE = 5;
    int FUNCTION_NODE = 6;

    int getType();

    Expression getValue();
}
