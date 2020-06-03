public class ParserMain {
    public static void main(String[] args) throws Exception {
        String s = "fun f(){print 2;} f(); print 1;";
        Parser p = new Parser(new Lexer(s));
        System.out.print(p.parse());
    }
}
