package dev.temez.snippet.layer;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.*;
import com.j256.ormlite.support.ConnectionSource;
import dev.temez.snippet.Main;
import dev.temez.snippet.util.semantics.Layer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;

/**
 * @author temez
 * @since 0.1dev
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RepositoryLayer extends Layer {

    ConnectionSource connectionSource;


    public RepositoryLayer(Main plugin) {
        super(plugin);
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void enable() {
        super.enable();
        LoggerFactory.setLogBackendFactory(JavaUtilLogBackend::new);
        LoggerFactory.setLogBackendType(LogBackendType.JAVA_UTIL);
        Logger.setGlobalLogLevel(Level.ERROR);
        connectionSource = new JdbcConnectionSource("jdbc:mysql://" + plugin.getSettings().getMySQLHost() + ":" + plugin.getSettings().getMySQLPort() + "/" + plugin.getSettings().getMySQLDatabase(), plugin.getSettings().getMySQLUser(), plugin.getSettings().getMySQLPassword());
    }

    @Override
    @SneakyThrows(Exception.class)
    public void disable() {
        super.disable();
        connectionSource.close();
    }
}
