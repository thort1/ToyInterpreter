public class VarDefin extends Statement {
    private VarName name;

    public VarDefin(VarName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VarDefin{" +
                "name=" + name +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException {
        if (env.contains(name.toString())) {
            throw new SyntaxException("variable: " + name + " was re-declared");
        }
        env.setDefine(name.toString(), null);
        return null;
    }
}
