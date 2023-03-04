package dev.temez.snippet.layer;


import dev.temez.snippet.Main;
import dev.temez.snippet.exception.ModuleStateException;
import dev.temez.snippet.module.Module;
import dev.temez.snippet.util.semantics.Layer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

/**
 * @author temez
 * @since 0.1dev
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@Getter
public class GameModuleLayer extends Layer {

    HashMap<Class<? extends Module>, Module> loadedModules = new HashMap<>();

    public GameModuleLayer(Main plugin) {
        super(plugin);
    }

    @SneakyThrows(ModuleStateException.class)
    public void load(Module module) {
        if (loadedModules.containsKey(module.getClass()))
            throw new ModuleStateException("Module is already loaded to loader");
        loadedModules.put(module.getClass(), module);
        loadedModules.get(module.getClass()).enable();
    }

    @SneakyThrows(ModuleStateException.class)
    public Module get(Class<? extends Module> moduleClass) {
        if (!loadedModules.containsKey(moduleClass)) throw new ModuleStateException("Module is not loaded to loader");
        return loadedModules.getOrDefault(moduleClass, null);
    }

    @Override
    public void disable() {
        super.disable();
        loadedModules.values().forEach(Module::disable);
    }
}
