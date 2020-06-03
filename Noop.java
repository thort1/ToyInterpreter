public class Noop extends Statement {

    @Override
    public String toString() {
        return "Noop";
    }

    @Override
    public Object eval(Environment env) {
        return null;
    }
}
