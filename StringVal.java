public class StringVal extends Expression implements Valuable {
    private String value;

    public StringVal(String value) {
        this.value = value;
    }

    public StringVal(Token t) {
        this.value = t.getValue();
    }

    @Override
    public String toString() {
        return "StringVal{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public Object eval(Environment env) {
        return value;
    }
}
