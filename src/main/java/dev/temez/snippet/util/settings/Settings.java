package dev.temez.snippet.util.settings;

import dev.temez.utilities.spigot.configuration.reflect.annotation.ConfigurationPath;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.1.2
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Settings {

    @ConfigurationPath(path = "database", key = "host")
    String mySQLHost;

    @ConfigurationPath(path = "database", key = "port")
    Integer mySQLPort;

    @ConfigurationPath(path = "database", key = "database")
    String mySQLDatabase;

    @ConfigurationPath(path = "database", key = "user")
    String mySQLUser;

    @ConfigurationPath(path = "database", key = "password")
    String mySQLPassword;
}
