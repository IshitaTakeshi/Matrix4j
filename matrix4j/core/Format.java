package matrix4j.core;


class Format {
    //exhibit given number using the English ordinal suffix
    public static String ordinal(int n) {
        if(n == 1) {
            return "1st";
        }
        if(n == 2) {
            return "2nd";
        }
        if(n == 3) {
            return "3rd";
        }
        return n + "th";
    }
}
