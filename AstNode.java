public abstract class AstNode {
    public abstract Object eval(Environment env) throws SyntaxException, BreakException, ReturnException;
}
