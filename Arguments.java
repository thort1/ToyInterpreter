import java.util.ArrayList;
import java.util.List;

public class Arguments extends Expression {
    private List<Expression> expressions;

    public Arguments(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        List<Object> args = new ArrayList<>();
        for (Expression e : expressions) {
            args.add(e.eval(env));
        }
        return args;
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "expressions=" + expressions +
                '}';
    }

    public int size() {
        return expressions.size();
    }
}
