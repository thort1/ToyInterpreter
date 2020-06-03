public class BooleanVal extends Expression implements Valuable {
    private boolean value;

    public BooleanVal(boolean value) {
        this.value = value;
    }

    public BooleanVal(Token t) {
        this.value = Boolean.parseBoolean(t.getValue());
    }

    @Override
    public String toString() {
        return "BooleanVal{" +
                "value=" + value +
                '}';
    }

    @Override
    public Object eval(Environment env) {
        return value;
    }
}
