public class VarName extends Expression {
    private String name;

    public VarName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object eval(Environment env) throws SyntaxException {
        return env.getValue(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
