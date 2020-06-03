public class Operations {
    public static Object add(Object a, Object b) {
        if (bothInt(a, b)) {
            return plus((Integer)a, (Integer)b);
        } else if (a instanceof Integer && b instanceof Float) {
            return plus((Integer) a, (Float) b);
        } else if (a instanceof Float && b instanceof Integer) {
            return plus((Integer) b, (Float) a);
        } else if (bothFloat(a, b)) {
            return plus((Float)a, (Float)b);
        } else if (a instanceof String && b instanceof Float) {
            return plus((String) a, (Float) b);
        } else if (a instanceof Float && b instanceof String) {
            return plus((String) b, (Float) a);
        } else if (a instanceof String && b instanceof Integer) {
            return plus((String) a, (Integer) b);
        } else if (a instanceof Integer && b instanceof String) {
            return plus((String) b, (Integer) a);
        } else if (bothString(a, b)) {
            return plus((String) b, (String) a);
        }
        return null;
    }

    public static Object subtract(Object a, Object b) {
        if (bothInt(a, b)) {
            return subtract((Integer) a, (Integer) b);
        } else if (a instanceof Integer && b instanceof Float) {
            return subtract((Integer) a, (Float) b);
        } else if (a instanceof Float && b instanceof Integer) {
            return subtract((Integer) b, (Float) a);
        } else if (bothFloat(a, b)) {
            return subtract((Float) a, (Float) b);
        }
        return null;
    }

    public static Object multiply(Object a, Object b) {
        if (bothInt(a, b)) {
            return multiply((Integer) a, (Integer) b);
        } else if (a instanceof Integer && b instanceof Float) {
            return multiply((Integer) a, (Float) b);
        } else if (a instanceof Float && b instanceof Integer) {
            return multiply((Integer) b, (Float) a);
        } else if (bothFloat(a, b)) {
            return multiply((Float) a, (Float) b);
        }
        return null;
    }

    public static Object divide(Object a, Object b) {
        if (bothInt(a, b)) {
            return divide((Integer) a, (Integer) b);
        } else if (a instanceof Integer && b instanceof Float) {
            return divide((Integer) a, (Float) b);
        } else if (a instanceof Float && b instanceof Integer) {
            return divide((Integer) b, (Float) a);
        } else if (bothFloat(a, b)) {
            return divide((Float) a, (Float) b);
        }
        return null;
    }

    public static Boolean areEqual(Object a, Object b) {
        return a.equals(b);
    }

    public static Boolean areNotEqual(Object a, Object b) {
        return !areEqual(a, b);
    }

    public static int plus(Integer a, Integer b) {
        return a + b;
    }

    public static float plus(Integer a, Float b) {
        return a + b;
    }

    public static float plus(Float a, Float b) {
        return a + b;
    }

    public static String plus(String a, Integer b) {
        return a + b;
    }

    public static String plus(String a, Float b) {
        return a + b;
    }

    public static String plus(String a, String b) {
        return a + b;
    }

    public static int divide(Integer a, Integer b) {
        return a/b;
    }

    public static float divide(Integer a, Float b) {
        return a/b;
    }

    public static float divide(Float a, Float b) {
        return a/b;
    }

    public static int multiply(Integer a, Integer b) {
        return a * b;
    }

    public static float multiply(Integer a, Float b) {
        return a * b;
    }

    public static float multiply(Float a, Float b) {
        return a * b;
    }

    public static int subtract(Integer a, Integer b) {
        return a - b;
    }

    public static float subtract(Integer a, Float b) {
        return a - b;
    }

    public static float subtract(Float a, Float b) {
        return a - b;
    }

    private static boolean bothFloat(Object a, Object b) {
        return a instanceof Float && b instanceof Float;
    }

    private static boolean bothInt(Object a, Object b) {
        return a instanceof Integer && b instanceof Integer;
    }

    private static boolean bothString(Object a, Object b) {
        return a instanceof String && b instanceof String;
    }

    public static Object signedOp(TokenTypes type, Object val) {
        if (val instanceof Integer) {
            return signedInt(type, (Integer)val);
        } else if (val instanceof Float) {
            return signedFloat(type, (Float)val);
        }
        return null;
    }

    public static Integer signedInt(TokenTypes type, Integer val) {
        return type == TokenTypes.MINUS ? val * -1: val;
    }

    public static Float signedFloat(TokenTypes type, Float val) {
        return type == TokenTypes.MINUS ? val * -1: val;
    }

}
