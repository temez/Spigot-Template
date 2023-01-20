package dev.temez.snippet.exception;

/**
 * @author temez
 * @since 0.0.1dev
 */
public class ModuleStateException extends Exception {

    public ModuleStateException(String state) {
        super(state);
    }
}
