import java.util.List;

public class FunCall extends Expression {
    private VarName name;
    private Arguments args;

    public FunCall(VarName name, Arguments args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return "FunCall{" +
                "name=" + name +
                ", args=" + args +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        FunDef fun = (FunDef) env.getValue(name.getName() + "()");
        List<String> params = fun.getParams();
        if (args.size() != params.size()) {
            throw new SyntaxException("Expected num of parameters: " + fun.paramSize() + ", actual: " + args.size());
        }
        List<Object> evalArgs = (List<Object>) args.eval(env);
        Environment funEnv = new Environment(env);
        for (int i = 0; i < args.size(); i++) {
            funEnv.setDefine(params.get(i), evalArgs.get(i));
        }
        try {
            fun.getStatements().eval(funEnv);
        } catch (ReturnException r) {
            return r.getValue();
        }
        return null;

    }
}
