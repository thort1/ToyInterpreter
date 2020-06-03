public class IntVal extends Expression implements Valuable {
    private int value;

    public IntVal(int value) {
        this.value = value;
    }

    public IntVal(Token t) {
        this.value = Integer.parseInt(t.getValue());
    }

    @Override
    public String toString() {
        return "IntVal{" +
                "value=" + value +
                '}';
    }

    @Override
    public Object eval(Environment env) {
        return value;
    }
}
