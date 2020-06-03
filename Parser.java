
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private int index;
    private ArrayList<Token> tokens;

    public Parser(Lexer l) throws Exception {
        tokens = l.lexify();
    }

    private Token currToken() {
        return token(0);
    }

    private Token token(int relIndex) {
        return tokens.get(index + relIndex);
    }

    private TokenTypes peekAheadType(int relIndex) {
        try {
            return token(relIndex).getType();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private Token consume() {
        Token curr = currToken();
        index++;
        return curr;
    }

    private Token consume(TokenTypes t) throws SyntaxException {
        Token curr = consume();
        if (curr.getType() != t) {
            throw new SyntaxException("Token: " + curr.getType() + ", expecting: " + t);
        }
        return curr;
    }

    private TokenTypes currType() {
        return currToken().getType();
    }

    public Statements parse() throws SyntaxException {
        Statements s = parseStatements();
        if (currType() != TokenTypes.EOF) {
            throw new SyntaxException("Unexpected EOF");
        }
        return s;
    }

    public Statements parseStatements() throws SyntaxException {
        Statements statements = new Statements();
        while (true) {
            Statement s = parseStatement();
            if (s != null) {
                if (!(s instanceof Noop)) {
                    statements.add(s);
                }
            } else {
                return statements;
            }
        }
    }

    public Statement parseStatement() throws SyntaxException {
        switch (currType()) {
            case EOF:
                return null;
            case SEMICOLON:
                consume(TokenTypes.SEMICOLON);
                return new Noop();
            case PRINT:
                return parsePrint();
            case VAR:
                return parseVar();
            case NAME:
                TokenTypes type = peekAheadType(1);
                if (type == TokenTypes.ASSIGN) {
                    return parseAssign();
                } else if (type == TokenTypes.LPAREN){
                    return parseFunCall();
                }
                break;
            case IF:
                return parseIf();
            case WHILE:
                return parseWhile();
            case BREAK:
                return parseBreak();
            case RETURN:
                return parseReturn();
            case FUN:
                return parseFun();
            default:
                return null;
        }
        return null;
    }

    public Statement parsePrint() throws SyntaxException {
        consume(TokenTypes.PRINT);
        Expression e = parseExpression();
        consume(TokenTypes.SEMICOLON);
        return new Print(e);
    }

    public Statement parseFun() throws SyntaxException {
        consume(TokenTypes.FUN);
        VarName name = parseName();
        consume(TokenTypes.LPAREN);
        Parameters p = parseParameters();
        consume(TokenTypes.RPAREN);
        consume(TokenTypes.LBRACE);
        Statements s = parseStatements();
        consume(TokenTypes.RBRACE);
        return new FunDef(name, p, s);
    }

    public Statement parseVar() throws SyntaxException {
        consume(TokenTypes.VAR);
        VarName name = parseName();
        consume(TokenTypes.SEMICOLON);
        return new VarDefin(name);
    }

    public VarName parseName() throws SyntaxException {
        return new VarName(consume(TokenTypes.NAME).getValue());
    }

    public Statement parseAssign() throws SyntaxException {
        VarName name = parseName();
        consume(TokenTypes.ASSIGN);
        Expression e = parseExpression();
        consume(TokenTypes.SEMICOLON);
        return new Assign(name, e);
    }

    public Statement parseWhile() throws SyntaxException {
        consume(TokenTypes.WHILE);
        Expression condition = parseExpression();
        consume(TokenTypes.LBRACE);
        Statements statements = parseStatements();
        consume(TokenTypes.RBRACE);
        return new While(condition, statements);
    }

    public Expression parseFunCall() throws SyntaxException {
        VarName name = parseName();
        consume(TokenTypes.LPAREN);
        Arguments args = parseArguments();
        consume(TokenTypes.RPAREN);
        return new FunCall(name, args);
    }

    public Statement parseReturn() throws SyntaxException {
        consume(TokenTypes.RETURN);
        Expression e = parseExpression();
        consume(TokenTypes.SEMICOLON);
        return new Return(e);
    }

    public Statement parseIf() throws SyntaxException {
        consume(TokenTypes.IF);
        Expression condition = parseExpression();
        consume(TokenTypes.LBRACE);
        Statements ifs = parseStatements();
        consume(TokenTypes.RBRACE);
        Statements elses = new Statements();
        if (currType() == TokenTypes.ELSE) {
            consume(TokenTypes.ELSE);
            consume(TokenTypes.LBRACE);
            elses = parseStatements();
            consume(TokenTypes.RBRACE);
        }
        return new If(condition, ifs, elses);
    }

    public Expression parseExpression() throws SyntaxException {
        Expression e = parseTerm();
        TokenTypes t = currType();
        switch(t) {
            case EQUAL:
            case NOT_EQUAL:
            case PLUS:
            case MULTIPLY:
            case DIVIDE:
            case MINUS:
                consume(t);
                Expression right = parseExpression();
                return new BinaryOperator(t, e, right);
            default:
                return e;
        }
    }

    public Expression parseTerm() throws SyntaxException {
        TokenTypes t = currType();
        switch (t) {
            case MINUS:
            case PLUS:
                consume(t);
                Expression e = parseExpression();
                return new UnaryOperator(t, e);
            case LPAREN:
                consume(TokenTypes.LPAREN);
                Expression exp = parseExpression();
                consume(TokenTypes.RPAREN);
                return exp;
            case INT:
                return new IntVal(consume(t));
            case DECIMAL:
                return new DecimalVal(consume(t));
            case STRING:
                return new StringVal(consume(t));
            case BOOLEAN:
                return new BooleanVal(consume(t));
            case NAME:
                TokenTypes type = peekAheadType(1);
                if (type == TokenTypes.LPAREN){
                    return parseFunCall();
                }
                return parseName();
            default:
                // ??? Maybe return empty expression (#that's not a thing)
                return null;
        }
    }

    private Expression parseBreak() throws SyntaxException {
        consume(TokenTypes.BREAK);
        consume(TokenTypes.SEMICOLON);
        return new Break();
    }

    private Arguments parseArguments() throws SyntaxException {
        List<Expression> exps = new ArrayList<>();
        while (currType() != TokenTypes.RPAREN) {
            exps.add(parseExpression());
            if (currType() != TokenTypes.RPAREN) {
                consume(TokenTypes.COMMA);
            }
        }
        return new Arguments(exps);
    }

    private Parameters parseParameters() throws SyntaxException {
        List<VarName> params = new ArrayList<>();
        while (currType() != TokenTypes.RPAREN) {
            params.add(parseName());
            if (currType() != TokenTypes.RPAREN) {
                consume(TokenTypes.COMMA);
            }
        }
        return new Parameters(params);
    }


}
