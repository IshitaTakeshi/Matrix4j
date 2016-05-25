package matrix4j.core;


class AssertError extends Error {
    public AssertError() {
        super();
    }
}


public class Assert {
    public static void assertTrue(boolean e) {
        if(!e) {
            throw new AssertError();
        }
    }
}
