package Functions;

public abstract class Expression {
    private Expression parent;
    private int parentExprId;

    public Expression() {
        parent = null;
        parentExprId = -1;
    }

    public abstract double eval(Input in);

    public abstract Complex eval(Complex in);

    public abstract Expression derivative(String var);

    public abstract Expression getExp(int exprId);

    public int getParentExprId() {
        return parentExprId;
    }

    public abstract void setExp(int exprId, Expression expr);

    public boolean isParentSet() {
        return parentExprId != -1;
    }

    public Expression getParent() {
        return parent;
    }

    public void setParent(Expression parent, int parentExprId) {
        this.parent = parent;
        this.parentExprId = parentExprId;
    }

    public abstract String toString();

    public abstract boolean equals(Object o);
}
