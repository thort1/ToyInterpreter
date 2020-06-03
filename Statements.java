import java.util.ArrayList;

public class Statements extends AstNode {
    private ArrayList<Statement> statements;

    public Statements() {
        statements = new ArrayList<>();
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public void add(Statement s) {
        statements.add(s);
    }

    @Override
    public String toString() {
        return "Statements{" +
                "statements=" + statements +
                '}';
    }

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException, ReturnException {
        Object o = null;
        for (Statement s : statements) {
           o = s.eval(env);
        }
        return o;
    }

    public boolean isEmpty() {
        return statements.isEmpty();
    }
}
