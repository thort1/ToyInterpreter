public class While extends Statement {
    private Expression condition;
    private Statements statements;

    public While(Expression condition, Statements statements) {
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "While{" +
                "condition=" + condition +
                ", statements=" + statements +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException {
        try {
            while ((Boolean) condition.eval(env)) {
                statements.eval(env);
            }
        } catch (BreakException | ReturnException b) {
            return null;
        }
        return null;
    }
}
