package dev.temez.snippet.repository;

import com.j256.ormlite.support.ConnectionSource;
import dev.temez.snippet.Main;
import dev.temez.snippet.module.GameModule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.0.1dev
 */
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
public abstract class Repository extends GameModule {

    final ConnectionSource connectionSource;

    public Repository(Main plugin, ConnectionSource connectionSource) {
        super(plugin);
        this.connectionSource = connectionSource;
    }
}
