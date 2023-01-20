package dev.temez.snippet.module;

import dev.temez.snippet.Main;
import dev.temez.snippet.exception.ModuleStateException;
import dev.temez.snippet.util.Module;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.1dev
 */
@FieldDefaults(level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public abstract class GameModule implements Module {

    final Main plugin;
    boolean enabled = false;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    @SneakyThrows(ModuleStateException.class)
    public void enable() {
        if (enabled) throw new ModuleStateException("Module is already enabled");
        enabled = true;
    }

    @Override
    @SneakyThrows(ModuleStateException.class)
    public void disable() {
        if (!enabled) throw new ModuleStateException("Module is already disabled");
        enabled = false;
    }
}
