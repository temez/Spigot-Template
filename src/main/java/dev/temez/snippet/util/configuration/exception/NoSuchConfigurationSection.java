package dev.temez.snippet.util.configuration.exception;

/**
 * @author temez
 * @since 0.1dev
 */
public class NoSuchConfigurationSection extends Throwable {

    public NoSuchConfigurationSection(String path, String file) {
        super("No such section by path \"" + path + "\" found in " + file + ".yml");
    }
}
