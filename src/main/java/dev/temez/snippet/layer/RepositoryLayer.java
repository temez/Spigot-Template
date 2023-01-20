package dev.temez.snippet.layer;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import dev.temez.snippet.Main;
import dev.temez.snippet.util.semantics.Layer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author temez
 * @since 0.0.1dev
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
        connectionSource = new JdbcConnectionSource("jdbc:mysql://" + plugin.getSettings().getMySQLHost() + ":" + plugin.getSettings().getMySQLPort() + "/" + plugin.getSettings().getMySQLDatabase(), plugin.getSettings().getMySQLUser(), plugin.getSettings().getMySQLPassword());
    }

    @Override
    @SneakyThrows(IOException.class)
    public void disable() {
        super.disable();
        connectionSource.close();
    }
}
