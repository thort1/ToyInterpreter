import java.util.List;

public class Parameters extends Expression {
    private List<VarName> params;

    public Parameters(List<VarName> params) {
        this.params = params;
    }


    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException {
        return null;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "params=" + params +
                '}';
    }

    public int size() {
        return params.size();
    }

    public List<VarName> getParams() {
        return params;
    }
}
