public class BinaryOperator extends Expression {
    private TokenTypes op;
    private Expression left;
    private Expression right;

    public BinaryOperator(TokenTypes op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryOperator{" +
                "op=" + op +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        Object left_val = left.eval(env);
        Object right_val = right.eval(env);
        switch (op) {
            case PLUS:
                return Operations.add(left_val, right_val);
            case MINUS:
                return Operations.subtract(left_val, right_val);
            case MULTIPLY:
                return Operations.multiply(left_val, right_val);
            case DIVIDE:
                return Operations.divide(left_val, right_val);
            case EQUAL:
                return Operations.areEqual(left_val, right_val);
            case NOT_EQUAL:
                return Operations.areNotEqual(left_val, right_val);
            default:
                throw new SyntaxException("Not yet implemented.");
        }
    }
}
