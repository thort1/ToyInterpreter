public class Interpreter {
    private Statements statements;

    public Interpreter(String s) throws Exception {
        Lexer l = new Lexer(s);
        Parser p = new Parser(l);
        this.statements = p.parse();
    }

    public void evalProgram() throws SyntaxException, BreakException, ReturnException {
        Environment env = new Environment();
        evaluate(statements, env);
    }

    public Object evaluate(AstNode s, Environment env) throws SyntaxException, BreakException, ReturnException {
        return s.eval(env);
    }


}
