package matrix4j.core;


class AssertError extends Error {
    public AssertError() {
        super();
    }
}


class Assert {
    static void assertTrue(boolean e) {
        if(!e) {
            throw new AssertError();
        }
    }
}
