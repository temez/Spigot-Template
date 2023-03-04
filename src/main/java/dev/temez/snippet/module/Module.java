package dev.temez.snippet.module;

/**
 * @author temez
 * @since 0.1dev
 */
public interface Module {

    boolean isEnabled();

    void enable();

    void disable();
}
