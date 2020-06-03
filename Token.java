public class Token {
    private TokenTypes type;
    private String value;

    public Token(TokenTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Token getEOF() {
        return new Token(TokenTypes.EOF, "EOF");
    }

    public TokenTypes getType() {
        return type;
    }

    public void setType(TokenTypes type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
