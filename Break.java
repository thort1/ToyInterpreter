public class Break extends Expression {

    @Override
    public Object eval(Environment env) throws SyntaxException, BreakException {
        throw new BreakException("Break called.");
    }
}
