import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private int index;
    private String input;

    private final Map<TokenTypes, Pattern> dictionary = new TreeMap<TokenTypes, Pattern>() {{
        put(TokenTypes.LPAREN, Pattern.compile("^\\("));
        put(TokenTypes.RPAREN, Pattern.compile("^\\)"));
        put(TokenTypes.LBRACE, Pattern.compile("^\\{"));
        put(TokenTypes.RBRACE, Pattern.compile("^}"));
        put(TokenTypes.COMMA, Pattern.compile("^,"));
        put(TokenTypes.SEMICOLON, Pattern.compile("^;"));
        put(TokenTypes.IF, Pattern.compile("^if"));
        put(TokenTypes.ELSE, Pattern.compile("^else"));
        put(TokenTypes.VAR, Pattern.compile("^var"));
        put(TokenTypes.PRINT, Pattern.compile("^print"));
        put(TokenTypes.WHILE, Pattern.compile("^while"));
        put(TokenTypes.BREAK, Pattern.compile("^break"));
        put(TokenTypes.FUN, Pattern.compile("^fun"));
        put(TokenTypes.RETURN, Pattern.compile("^return"));
        put(TokenTypes.BOOLEAN, Pattern.compile("(^true)|(^false)"));
        put(TokenTypes.INT, Pattern.compile("^\\d+"));
        put(TokenTypes.DECIMAL, Pattern.compile("(^\\d+\\.\\d*)|(^\\d*\\.\\d+)"));
        put(TokenTypes.STRING, Pattern.compile("^\".*\""));
        put(TokenTypes.EQUAL, Pattern.compile("^=="));
        put(TokenTypes.NOT_EQUAL, Pattern.compile("^!="));
        put(TokenTypes.PLUS, Pattern.compile("^\\+"));
        put(TokenTypes.MINUS, Pattern.compile("^-"));
        put(TokenTypes.MULTIPLY, Pattern.compile("^\\*"));
        put(TokenTypes.DIVIDE, Pattern.compile("^/"));
        put(TokenTypes.ASSIGN, Pattern.compile("^="));
        put(TokenTypes.WHITE_SPACE, Pattern.compile("^\\s"));
        put(TokenTypes.LINE_COMMENT, Pattern.compile("^//.*\\n"));
        put(TokenTypes.BLOCK_COMMENT, Pattern.compile("^/\\*.*(\\*/)+?"));
        put(TokenTypes.NAME, Pattern.compile("^\\w+"));
    }};

    public Lexer(String input) {
        this.index = 0;
        this.input = input;
    }


    private Token nextToken() throws Exception {
        if (index >= input.length()) {
            return Token.getEOF();
        }
        Token t = null;
        int maxLength = 0;
        for (TokenTypes type : dictionary.keySet()) {
            Matcher m = dictionary.get(type).matcher(input.substring(index));
            if (m.lookingAt()) {
                String result = m.group();
                if (result.length() > maxLength) {
                    maxLength = result.length();
                    t = new Token(type, result);
                }
            }
        }
        if (t == null) {
            throw new Exception("Unknown character(s)");
        }
        index += maxLength;
        return t;
    }

    // TODO
    // make !awful
    private Token next() throws Exception {
        while (true) {
            Token t = nextToken();
            switch (t.getType()) {
                case WHITE_SPACE:
                case BLOCK_COMMENT:
                case LINE_COMMENT:
                    continue;
                default:
                    return t;
            }
        }
    }

    // TODO
    // Check generics
    public ArrayList<Token> lexify() throws Exception {
        ArrayList<Token> tokens = new ArrayList<>();
        while (true) {
            Token t = next();
            tokens.add(t);
            if (t.getType() == TokenTypes.EOF) {
                // Resets index to be able to reuse lexer
                index = 0;
                return tokens;
            }
        }
    }

}