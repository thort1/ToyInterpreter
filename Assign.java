public class Assign extends Statement {
    private VarName name;
    private Expression exp;

    public Assign(VarName name, Expression exp) {
        this.name = name;
        this.exp = exp;
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        env.setValue(name.getName(), exp.eval(env));
        return null;
    }

    @Override
    public String toString() {
        return "Assign{" +
                "name=" + name +
                ", exp=" + exp +
                '}';
    }
}
