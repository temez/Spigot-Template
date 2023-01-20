package dev.temez.snippet.util;

/**
 * @author temez
 * @since 0.0.1dev
 */
public interface Module {

    boolean isEnabled();

    void enable();

    void disable();
}
