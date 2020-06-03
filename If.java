public class If extends Statement {
    private Expression condition;
    private Statements ifs;
    private Statements elses;

    public If(Expression condition, Statements ifs, Statements elses) {
        this.condition = condition;
        this.ifs = ifs;
        this.elses = elses;
    }

    @Override
    public String toString() {
        return "If{" +
                "condition=" + condition +
                ", ifs=" + ifs +
                ", elses=" + elses +
                '}';
    }


    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        Object cond = condition.eval(env);
        if ((Boolean) cond) {
            return ifs.eval(env);
        } else {
            if (!elses.isEmpty()) {
                return elses.eval(env);
            }
            return null;
        }
    }
}
