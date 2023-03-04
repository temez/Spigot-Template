package dev.temez.snippet.util.configuration.exception;

/**
 * @author temez
 * @since 0.1dev
 */
public class IncomparableFieldType extends Throwable {

    public IncomparableFieldType(String field) {
        super("Found incomparable types while processing \"" + field + "\"");
    }
}
