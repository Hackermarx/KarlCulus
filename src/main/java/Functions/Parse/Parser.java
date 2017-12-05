package Functions.Parse;

import Functions.Expression;
import Functions.Function;
import Functions.Parse.ExpressionTree.ExpressionNode;
import Functions.Parse.ExpressionTree.*;
import jdk.nashorn.internal.runtime.ParserException;

import java.util.LinkedList;
import java.util.List;

public class Parser {
    private LinkedList<Token> tokens;
    private Token lookahead;

    private static Parser parser = new Parser();
    private static Tokenizer tokenizer = new Tokenizer();

    public static Function parse(String input) {
        return parse(input, "x");
    }

    public static Function parse(String input, String... ins) {
        for (String s : ins) {
            if (!input.contains(s)) {
                throw new IllegalArgumentException("Arguments for function not given in function");
            }
        }
        tokenizer.tokenize(input);
        return new Function(parser.parse(tokenizer.getTokens()), ins);
    }

    public Expression parse(List<Token> tokens)
    {
        this.tokens = new LinkedList<>(tokens);
        lookahead = this.tokens.getFirst();

        Expression ret = expression().getValue();

        if (lookahead.token != Token.EPSILON) {
            throw new ParserException("Unexpected symbol is found:" + lookahead.toString());
        }

        return ret;
    }

    private ExpressionNode expression() {
        // expression -> signed_term sum_op
        ExpressionNode expr = signedTerm();
        return sumOp(expr);
    }

    private ExpressionNode sumOp(ExpressionNode expr) {
        if (lookahead.token == Token.PLUSMINUS) {
            // sum_op -> PLUSMINUS term sum_op
            AdditionExpressionNode sum;
            if (expr.getType() == ExpressionNode.ADDITION_NODE) {
                sum = (AdditionExpressionNode) expr;
            } else {
                sum = new AdditionExpressionNode(expr, true);
            }

            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            sum.add(t, positive);

            return sumOp(sum);
        } else {
            // sum_op -> EPSILON
            return expr;
        }
    }

    private ExpressionNode signedTerm() {
        if (lookahead.token == Token.PLUSMINUS) {
            // signed_term -> PLUSMINUS term
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            if (positive) {
                return t;
            } else {
                return new AdditionExpressionNode(t, false);
            }
        } else {
            // signed_term -> term
            return term();
        }
    }

    private ExpressionNode term() {
        // term -> factor term_op
        ExpressionNode f = factor();
        return termOp(f);
    }

    private ExpressionNode termOp(ExpressionNode expression) {
        if (lookahead.token == Token.MULTDIV) {
            // term_op -> MULTDIV factor term_op
            MultiplicationExpressionNode prod;

            if (expression.getType() == ExpressionNode.MULTIPLICATION_NODE) {
                prod = (MultiplicationExpressionNode) expression;
            } else {
                prod = new MultiplicationExpressionNode(expression, true);
            }

            boolean positive = lookahead.sequence.equals("*");
            nextToken();
            ExpressionNode f = signedFactor();
            prod.add(f, positive);

            return termOp(prod);
        } else {
            // term_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode signedFactor() {
        if (lookahead.token == Token.PLUSMINUS) {
            // signed_factor -> PLUSMINUS factor
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = factor();
            if (positive) {
                return t;
            } else {
                return new AdditionExpressionNode(t, false);
            }
        } else {
            // signed_factor -> factor
            return factor();
        }
    }

    private ExpressionNode factor() {
        // factor -> argument factor_op
        ExpressionNode a = argument();
        return factorOp(a);
    }

    private ExpressionNode factorOp(ExpressionNode expression) {
        if (lookahead.token == Token.RAISED) {
            // factor_op -> RAISED factor
            nextToken();
            ExpressionNode exponent = signedFactor();

            return new ExponentiationExpressionNode(expression, exponent);
        } else {
            // factor_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode argument() {
        if (lookahead.token == Token.FUNCTION) {
            // argument -> FUNCTION argument
            int function = FunctionExpressionNode.stringToFunction(lookahead.sequence);
            nextToken();
            ExpressionNode expr = argument();
            return new FunctionExpressionNode(function, expr);
        } else if (lookahead.token == Token.OPEN_BRACKET) {
            // argument -> OPEN_BRACKET sum CLOSE_BRACKET
            nextToken();
            ExpressionNode expr = expression();
            if (lookahead.token != Token.CLOSE_BRACKET) {
                throw new ParserException("Closing brackets expected" + lookahead);
            }
            nextToken();
            return expr;
        }

        // argument -> value
        return value();
    }

    private ExpressionNode value() {
        // argument -> NUMBER
        if (lookahead.token == Token.NUMBER) {
            ExpressionNode expr = new ConstantExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }

        // argument -> VARIABLE
        if (lookahead.token == Token.VARIABLE) {
            ExpressionNode expr = new VariableExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }

        if (lookahead.token == Token.EPSILON) {
            throw new ParserException("Unexpected end of input");
        } else {
            throw new ParserException("Unexpected symbol %s found: " + lookahead);
        }
    }

    private void nextToken() {
        tokens.pop();
        // at the end of input we return an epsilon token
        if (tokens.isEmpty())
            lookahead = new Token(Token.EPSILON, "");
        else
            lookahead = tokens.getFirst();
    }
}
