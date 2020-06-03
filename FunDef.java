import java.util.ArrayList;
import java.util.List;

public class FunDef extends Statement {
    private VarName name;
    private Parameters params;
    private Statements statements;

    public FunDef(VarName name, Parameters params, Statements statements) {
        this.name = name;
        this.params = params;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "FunDef{" +
                "name=" + name +
                ", params=" + params +
                ", statements=" + statements +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException {
        env.setDefine(name.getName()  + "()", this);
        return null;
    }

    public int paramSize() {
        return params.size();
    }

    public List<String> getParams() {
        List<String> strParams = new ArrayList<>();
        for (VarName v : params.getParams()) {
            strParams.add(v.getName());
        }
        return strParams;
    }

    public Statements getStatements() {
        return statements;
    }
}
