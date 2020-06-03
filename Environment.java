import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> environ;
    private Environment parent;

    public Environment() {
        environ = new HashMap<>();
        parent = null;
    }

    public Environment(Environment parent) {
        environ = new HashMap<>();
        this.parent = parent;
    }

    public Object getValue(String s) throws SyntaxException {
        if (environ.containsKey(s)) {
            return environ.get(s);
        } else {
            if (parent != null) {
                return parent.getValue(s);
            } else {
                throw new SyntaxException("Undeclared variable: " + s);
            }
        }
    }

    public void setValue(String s, Object v) throws SyntaxException {
        if (environ.containsKey(s)) {
            environ.put(s, v);
            return;
        } else {
            if (parent != null) {
                parent.setValue(s, v);
            } else {
                throw new SyntaxException("Undeclared variable: " + s);
            }
            return;
        }
    }

    public void setDefine(String s, Object v) {
        environ.put(s, v);
    }

    public boolean contains(String s) {
        return environ.containsKey(s);
    }

    @Override
    public String toString() {
        return "Environment{" +
                "environ=" + environ +
                '}';
    }
}
