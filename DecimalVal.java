public class DecimalVal extends Expression implements Valuable {
    private float value;

    public DecimalVal(float value) {
        this.value = value;
    }

    public DecimalVal(Token t) {
        this.value = Float.parseFloat(t.getValue());
    }

    @Override
    public String toString() {
        return "DecimalVal{" +
                "value=" + value +
                '}';
    }

    @Override
    public Object eval(Environment env) {
        return value;
    }
}
