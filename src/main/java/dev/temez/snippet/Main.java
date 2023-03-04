package dev.temez.snippet;

import com.samjakob.spigui.SpiGUI;
import dev.temez.snippet.layer.GameModuleLayer;
import dev.temez.snippet.layer.RepositoryLayer;
import dev.temez.snippet.layer.UtilityLayer;
import dev.temez.snippet.util.ChatUtility;
import dev.temez.snippet.util.settings.Settings;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class Main extends JavaPlugin {

    UtilityLayer utilityLayer;
    GameModuleLayer gameModuleLayer;
    RepositoryLayer repositoryLayer;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        utilityLayer = new UtilityLayer(this);
        utilityLayer.enable();
        repositoryLayer = new RepositoryLayer(this);
        repositoryLayer.enable();
        gameModuleLayer = new GameModuleLayer(this);
        gameModuleLayer.enable();
    }

    @Override
    public void onDisable() {
        gameModuleLayer.disable();
        repositoryLayer.disable();
        utilityLayer.disable();

    }


    /**
     * Шорткаты для утилит
     */

    public ChatUtility getChatUtility() {
        return utilityLayer.getChatUtility();
    }

    public SpiGUI getSpiGui() {
        return utilityLayer.getSpiGUI();
    }

    public Settings getSettings() {
        return utilityLayer.getSettings();
    }

}
