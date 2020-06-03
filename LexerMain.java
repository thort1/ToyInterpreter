public class LexerMain {
    public static void main(String[] args) throws Exception {
        String s = "( ) * if true false + a(b(c)) define 33 78.2 -0.1 -12 \"hello\" test1 else // hi \n a)b /* hello */ == - * / = var ; ,";
        Lexer l = new Lexer(s);
        System.out.println(l.lexify());
    }
}
