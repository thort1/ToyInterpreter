public class InterpreterMain {
    public static void main(String[] args) throws Exception {
        //String s = "fun factorial(n) {var a; a = 100; if n != 0 { return n * factorial(n-1); } else { return 1; } }  var a; a = 4; print factorial(a + 1); print a;";
         String s = "fun f(a) {var y; y = a; print y;} fun b(x){ if x == 12 {return x + 3;} else { return 0;}} f(b(x)); print 1;";
        //String s = "f();";
        Interpreter i = new Interpreter(s);
        i.evalProgram();
    }
}
