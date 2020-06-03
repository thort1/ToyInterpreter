public class UnaryOperator extends Expression {
    private TokenTypes op;
    private Expression expression;

    public UnaryOperator(TokenTypes op, Expression expression) {
        this.op = op;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "UnaryOperator{" +
                "op=" + op +
                ", expression=" + expression +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        Object result = expression.eval(env);
        return Operations.signedOp(op, result);
    }
}
