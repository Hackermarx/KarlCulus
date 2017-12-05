package Functions.Trig;

import Functions.*;

abstract class TrigFunction extends Expression {
    private boolean isArc;
    protected Expression child;

    public TrigFunction(Expression child, boolean isArc) {
        super();
        this.child = child;
        this.isArc = isArc;
        this.child.setParent(this, 0);
    }

    public boolean isArc() {
        return isArc;
    }

    @Override
    public double eval(Input in) {
        if (isArc) {
            return arcEval(in);
        } else {
            return stdEval(in);
        }
    }

    public abstract double stdEval(Input in);

    public abstract double arcEval(Input in);

    @Override
    public Complex eval(Complex in) {
        if (isArc) {
            return arcEval(in);
        } else {
            return stdEval(in);
        }
    }

    public abstract Complex stdEval(Complex in);

    public abstract Complex arcEval(Complex in);

    @Override
    public Expression getExp(int exprId) {
        return child;
    }

    @Override
    public void setExp(int exprId, Expression expr) {
        this.child = expr;
    }

    public String toString() {
        return isArc ? "a" : "";
    }
}
