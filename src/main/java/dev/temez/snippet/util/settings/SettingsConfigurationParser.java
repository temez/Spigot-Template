package dev.temez.snippet.util.settings;

import dev.temez.utilities.spigot.configuration.reflect.ReflectConfigurationParser;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author temez
 * @since 0.0.1dev
 */
public class SettingsConfigurationParser extends ReflectConfigurationParser<Settings> {

    public SettingsConfigurationParser(Settings configuration, FileConfiguration fileConfiguration) {
        super(configuration, fileConfiguration);
    }
}
