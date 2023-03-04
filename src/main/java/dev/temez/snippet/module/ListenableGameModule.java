package dev.temez.snippet.module;

import dev.temez.snippet.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author temez
 * @since 0.1dev
 */
public abstract class ListenableGameModule extends GameModule implements Listener {

    public ListenableGameModule(Main plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        super.enable();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
