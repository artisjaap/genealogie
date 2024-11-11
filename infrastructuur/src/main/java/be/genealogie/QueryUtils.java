package be.genealogie;

public class QueryUtils {

    public final static String DB_WILDCARD = "%";

    public static String metDbWildcard(String string) {
        return string + DB_WILDCARD;
    }
}
