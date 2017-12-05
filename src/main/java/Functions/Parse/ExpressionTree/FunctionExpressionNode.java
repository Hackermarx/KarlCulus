package Functions.Parse.ExpressionTree;

import Functions.*;
import Functions.Trig.*;
import jdk.nashorn.internal.runtime.ParserException;

public class FunctionExpressionNode implements ExpressionNode {
    private static final int SIN = 1;
    private static final int COS = 2;
    private static final int TAN = 3;

    private static final int SEC = 4;
    private static final int CSC = 5;
    private static final int COT = 6;

    private static final int ASIN = 7;
    private static final int ACOS = 8;
    private static final int ATAN = 9;

    private static final int ASEC = 10;
    private static final int ACSC = 11;
    private static final int ACOT = 12;

    private static final int SQRT = 13;
    private static final int EXP = 14;

    private static final int LN = 15;
    private static final int LOG = 16;

    private int function;
    private ExpressionNode argument;

    public FunctionExpressionNode(int function, ExpressionNode argument) {
        this.function = function;
        this.argument = argument;
    }

    public int getType() {
        return ExpressionNode.FUNCTION_NODE;
    }

    public Expression getValue() {
        switch (function) {
            case SIN:   return new Sine(argument.getValue());
            case COS:   return new Cosine(argument.getValue());
            case TAN:   return new Tangent(argument.getValue());
            case SEC:   return new Secant(argument.getValue());
            case CSC:   return new Cosecant(argument.getValue());
            case COT:   return new Cotangent(argument.getValue());
            case ASIN:  return new Sine(argument.getValue(), true);
            case ACOS:  return new Cosine(argument.getValue(), true);
            case ATAN:  return new Tangent(argument.getValue(), true);
            case ASEC:  return new Secant(argument.getValue(), true);
            case ACSC:  return new Cosecant(argument.getValue(), true);
            case ACOT:  return new Cotangent(argument.getValue(), true);
            case SQRT:  return new Power(argument.getValue(), 0.5);
            case EXP:   return new NatExp(argument.getValue());
            case LN:    return new NatLogarithm(argument.getValue());
            case LOG:   return new Logarithm(10, argument.getValue());
        }
        throw new IndexOutOfBoundsException("Invalid function id " + function + "!");
    }

    public static int stringToFunction(String str) {
        if (str.equals("sin")) return FunctionExpressionNode.SIN;
        if (str.equals("cos")) return FunctionExpressionNode.COS;
        if (str.equals("tan")) return FunctionExpressionNode.TAN;

        if (str.equals("sec")) return FunctionExpressionNode.SEC;
        if (str.equals("csc")) return FunctionExpressionNode.CSC;
        if (str.equals("cot")) return FunctionExpressionNode.COT;

        if (str.equals("asin")) return FunctionExpressionNode.ASIN;
        if (str.equals("acos")) return FunctionExpressionNode.ACOS;
        if (str.equals("atan")) return FunctionExpressionNode.ATAN;

        if (str.equals("asec")) return FunctionExpressionNode.ASEC;
        if (str.equals("acsc")) return FunctionExpressionNode.ACSC;
        if (str.equals("acot")) return FunctionExpressionNode.ACOT;

        if (str.equals("sqrt")) return FunctionExpressionNode.SQRT;
        if (str.equals("exp")) return FunctionExpressionNode.EXP;

        if (str.equals("ln")) return FunctionExpressionNode.LN;
        if (str.equals("log")) return FunctionExpressionNode.LOG;

        throw new ParserException("Unexpected Expression "+str+" found!");
    }

    public static String getAllFunctions() {
        return "sin|cos|tan|sec|csc|cot|asin|acos|atan|asec|acsc|acot|sqrt|exp|ln|log";
    }
}