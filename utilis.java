package application;


final class Utils {

    private Utils() {
        throw new UnsupportedOperationException();
    }

    static boolean isStringValid(final String str) {
        return str != null && !str.trim().isEmpty();
    }

}
