package dev.temez.snippet;

import dev.temez.snippet.layer.GameModuleLayer;
import dev.temez.snippet.layer.RepositoryLayer;
import dev.temez.snippet.util.settings.Settings;
import dev.temez.utilities.spigot.configuration.reflect.ReflectConfigurationParser;
import dev.temez.utilities.spigot.configuration.reflect.exception.IncomparableFieldType;
import dev.temez.utilities.spigot.configuration.reflect.exception.NoSuchConfigurationSection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class Main extends JavaPlugin {

    Settings settings;
    GameModuleLayer gameModuleLayer;
    RepositoryLayer repositoryLayer;
    @Override
    @SneakyThrows({NoSuchConfigurationSection.class, IncomparableFieldType.class})
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        settings = new ReflectConfigurationParser<>(new Settings(), getConfig()).parse().getConfiguration();
        gameModuleLayer = new GameModuleLayer(this);
        gameModuleLayer.enable();
        repositoryLayer = new RepositoryLayer(this);
        repositoryLayer.enable();
    }

    @Override
    public void onDisable() {
        gameModuleLayer.disable();
        repositoryLayer.disable();
    }
}
