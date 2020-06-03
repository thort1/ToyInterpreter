public class Print extends Statement {
    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Print{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public Valuable eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        System.out.println(expression.eval(env));
        return null;
    }
}
