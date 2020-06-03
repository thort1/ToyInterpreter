public class Return extends Statement {
    private Expression exp;

    public Return(Expression exp) {
        this.exp = exp;
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        throw new ReturnException(exp.eval(env));
    }

    @Override
    public String toString() {
        return "Return{" +
                "exp=" + exp +
                '}';
    }
}
